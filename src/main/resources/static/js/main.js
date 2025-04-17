$(document).ready(function() {
    // Load dữ liệu ban đầu
    loadJpaStudents();
    
    // Sự kiện chuyển tab
    $('#jdbc-tab').on('shown.bs.tab', function (e) {
        loadJdbcStudents();
    });
    
    $('#compare-tab').on('shown.bs.tab', function (e) {
        loadCompareData();
    });

    // JPA Form Submit
    $('#jpaStudentForm').submit(function(e) {
        e.preventDefault();
        saveJpaStudent();
    });

    // JDBC Form Submit
    $('#jdbcStudentForm').submit(function(e) {
        e.preventDefault();
        saveJdbcStudent();
    });

    // Nút Clear JPA Form
    $('#jpaClearBtn').click(function() {
        clearJpaForm();
    });

    // Nút Clear JDBC Form
    $('#jdbcClearBtn').click(function() {
        clearJdbcForm();
    });

    // Tìm kiếm JPA
    $('#jpaSearchBtn').click(function() {
        searchJpaStudents();
    });

    // Reset tìm kiếm JPA
    $('#jpaResetSearchBtn').click(function() {
        $('#jpaSearchInput').val('');
        loadJpaStudents();
    });

    // Tìm kiếm JDBC
    $('#jdbcSearchBtn').click(function() {
        searchJdbcStudents();
    });

    // Reset tìm kiếm JDBC
    $('#jdbcResetSearchBtn').click(function() {
        $('#jdbcSearchInput').val('');
        loadJdbcStudents();
    });
});

// ============ JPA Functions ============

function loadJpaStudents() {
    $.ajax({
        url: '/api/students/jpa',
        type: 'GET',
        success: function(data) {
            displayJpaStudents(data);
        },
        error: function(error) {
            console.error('Error loading JPA students:', error);
            alert('Không thể tải dữ liệu sinh viên từ JPA repository!');
        }
    });
}

function displayJpaStudents(students) {
    let tableBody = $('#jpaStudentTableBody');
    tableBody.empty();
    
    if (students.length === 0) {
        tableBody.append('<tr><td colspan="6" class="text-center">Không có dữ liệu</td></tr>');
        return;
    }
    
    students.forEach(function(student) {
        tableBody.append(`
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.major}</td>
                <td>${student.gpa}</td>
                <td>
                    <button class="btn btn-sm btn-info edit-jpa-btn" data-id="${student.id}">Sửa</button>
                    <button class="btn btn-sm btn-danger delete-jpa-btn" data-id="${student.id}">Xóa</button>
                </td>
            </tr>
        `);
    });
    
    // Gắn sự kiện cho nút Sửa JPA
    $('.edit-jpa-btn').click(function() {
        let studentId = $(this).data('id');
        editJpaStudent(studentId);
    });
    
    // Gắn sự kiện cho nút Xóa JPA
    $('.delete-jpa-btn').click(function() {
        let studentId = $(this).data('id');
        deleteJpaStudent(studentId);
    });
}

function saveJpaStudent() {
    let studentId = $('#jpaStudentId').val();
    let student = {
        name: $('#jpaName').val(),
        email: $('#jpaEmail').val(),
        major: $('#jpaMajor').val(),
        gpa: $('#jpaGpa').val()
    };
    
    let url = '/api/students/jpa';
    let type = 'POST';
    
    if (studentId) {
        url = `/api/students/jpa/${studentId}`;
        type = 'PUT';
    }
    
    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(student),
        success: function(data) {
            alert(studentId ? 'Cập nhật sinh viên thành công!' : 'Thêm sinh viên thành công!');
            clearJpaForm();
            loadJpaStudents();
        },
        error: function(error) {
            console.error('Error saving JPA student:', error);
            alert('Lưu thông tin sinh viên thất bại!');
        }
    });
}

function editJpaStudent(id) {
    $.ajax({
        url: `/api/students/jpa/${id}`,
        type: 'GET',
        success: function(student) {
            $('#jpaStudentId').val(student.id);
            $('#jpaName').val(student.name);
            $('#jpaEmail').val(student.email);
            $('#jpaMajor').val(student.major);
            $('#jpaGpa').val(student.gpa);
            $('#jpaSaveBtn').text('Cập nhật');
        },
        error: function(error) {
            console.error('Error getting JPA student:', error);
            alert('Không thể tải thông tin sinh viên!');
        }
    });
}

function deleteJpaStudent(id) {
    if (confirm('Bạn có chắc chắn muốn xóa sinh viên này?')) {
        $.ajax({
            url: `/api/students/jpa/${id}`,
            type: 'DELETE',
            success: function() {
                alert('Xóa sinh viên thành công!');
                loadJpaStudents();
            },
            error: function(error) {
                console.error('Error deleting JPA student:', error);
                alert('Xóa sinh viên thất bại!');
            }
        });
    }
}

function searchJpaStudents() {
    let searchName = $('#jpaSearchInput').val();
    if (!searchName) {
        loadJpaStudents();
        return;
    }
    
    $.ajax({
        url: `/api/students/jpa/search/name?name=${searchName}`,
        type: 'GET',
        success: function(data) {
            displayJpaStudents(data);
        },
        error: function(error) {
            console.error('Error searching JPA students:', error);
            alert('Tìm kiếm sinh viên thất bại!');
        }
    });
}

function clearJpaForm() {
    $('#jpaStudentId').val('');
    $('#jpaName').val('');
    $('#jpaEmail').val('');
    $('#jpaMajor').val('');
    $('#jpaGpa').val('');
    $('#jpaSaveBtn').text('Lưu');
}

// ============ JDBC Functions ============

function loadJdbcStudents() {
    $.ajax({
        url: '/api/students/jdbc',
        type: 'GET',
        success: function(data) {
            displayJdbcStudents(data);
        },
        error: function(error) {
            console.error('Error loading JDBC students:', error);
            alert('Không thể tải dữ liệu sinh viên từ JDBC repository!');
        }
    });
}

function displayJdbcStudents(students) {
    let tableBody = $('#jdbcStudentTableBody');
    tableBody.empty();
    
    if (students.length === 0) {
        tableBody.append('<tr><td colspan="6" class="text-center">Không có dữ liệu</td></tr>');
        return;
    }
    
    students.forEach(function(student) {
        tableBody.append(`
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.major}</td>
                <td>${student.gpa}</td>
                <td>
                    <button class="btn btn-sm btn-info edit-jdbc-btn" data-id="${student.id}">Sửa</button>
                    <button class="btn btn-sm btn-danger delete-jdbc-btn" data-id="${student.id}">Xóa</button>
                </td>
            </tr>
        `);
    });
    
    // Gắn sự kiện cho nút Sửa JDBC
    $('.edit-jdbc-btn').click(function() {
        let studentId = $(this).data('id');
        editJdbcStudent(studentId);
    });
    
    // Gắn sự kiện cho nút Xóa JDBC
    $('.delete-jdbc-btn').click(function() {
        let studentId = $(this).data('id');
        deleteJdbcStudent(studentId);
    });
}

function saveJdbcStudent() {
    let studentId = $('#jdbcStudentId').val();
    let student = {
        name: $('#jdbcName').val(),
        email: $('#jdbcEmail').val(),
        major: $('#jdbcMajor').val(),
        gpa: $('#jdbcGpa').val()
    };
    
    let url = '/api/students/jdbc';
    let type = 'POST';
    
    if (studentId) {
        url = `/api/students/jdbc/${studentId}`;
        type = 'PUT';
    }
    
    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(student),
        success: function(data) {
            alert(studentId ? 'Cập nhật sinh viên thành công!' : 'Thêm sinh viên thành công!');
            clearJdbcForm();
            loadJdbcStudents();
        },
        error: function(error) {
            console.error('Error saving JDBC student:', error);
            alert('Lưu thông tin sinh viên thất bại!');
        }
    });
}

function editJdbcStudent(id) {
    $.ajax({
        url: `/api/students/jdbc/${id}`,
        type: 'GET',
        success: function(student) {
            $('#jdbcStudentId').val(student.id);
            $('#jdbcName').val(student.name);
            $('#jdbcEmail').val(student.email);
            $('#jdbcMajor').val(student.major);
            $('#jdbcGpa').val(student.gpa);
            $('#jdbcSaveBtn').text('Cập nhật');
        },
        error: function(error) {
            console.error('Error getting JDBC student:', error);
            alert('Không thể tải thông tin sinh viên!');
        }
    });
}

function deleteJdbcStudent(id) {
    if (confirm('Bạn có chắc chắn muốn xóa sinh viên này?')) {
        $.ajax({
            url: `/api/students/jdbc/${id}`,
            type: 'DELETE',
            success: function() {
                alert('Xóa sinh viên thành công!');
                loadJdbcStudents();
            },
            error: function(error) {
                console.error('Error deleting JDBC student:', error);
                alert('Xóa sinh viên thất bại!');
            }
        });
    }
}

function searchJdbcStudents() {
    let searchName = $('#jdbcSearchInput').val();
    if (!searchName) {
        loadJdbcStudents();
        return;
    }
    
    $.ajax({
        url: `/api/students/jdbc/search/name?name=${searchName}`,
        type: 'GET',
        success: function(data) {
            displayJdbcStudents(data);
        },
        error: function(error) {
            console.error('Error searching JDBC students:', error);
            alert('Tìm kiếm sinh viên thất bại!');
        }
    });
}

function clearJdbcForm() {
    $('#jdbcStudentId').val('');
    $('#jdbcName').val('');
    $('#jdbcEmail').val('');
    $('#jdbcMajor').val('');
    $('#jdbcGpa').val('');
    $('#jdbcSaveBtn').text('Lưu');
}

// ============ Compare Functions ============

function loadCompareData() {
    $.ajax({
        url: '/api/students/compare',
        type: 'GET',
        success: function(data) {
            displayCompareData(data);
        },
        error: function(error) {
            console.error('Error loading compare data:', error);
            alert('Không thể tải dữ liệu so sánh!');
        }
    });
}

function displayCompareData(data) {
    let jpaTableBody = $('#compareJpaTableBody');
    let jdbcTableBody = $('#compareJdbcTableBody');
    
    jpaTableBody.empty();
    jdbcTableBody.empty();
    
    // Display JPA data
    if (data.jpa.length === 0) {
        jpaTableBody.append('<tr><td colspan="4" class="text-center">Không có dữ liệu</td></tr>');
    } else {
        data.jpa.forEach(function(student) {
            jpaTableBody.append(`
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.major}</td>
                    <td>${student.gpa}</td>
                </tr>
            `);
        });
    }
    
    // Display JDBC data
    if (data.jdbc.length === 0) {
        jdbcTableBody.append('<tr><td colspan="4" class="text-center">Không có dữ liệu</td></tr>');
    } else {
        data.jdbc.forEach(function(student) {
            jdbcTableBody.append(`
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.major}</td>
                    <td>${student.gpa}</td>
                </tr>
            `);
        });
    }
} 