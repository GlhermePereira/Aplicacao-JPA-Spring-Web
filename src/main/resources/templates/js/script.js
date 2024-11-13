$(document).ready(function() {
    const API_URL = 'http://127.0.0.1:8080/alunos'; // Adjust the URL as per your backend endpoint

    // Function to fetch and display users
    function fetchUsers() {
        $.ajax({
            url: API_URL,
            method: 'GET',
            success: function(users) {
                let rows = '';
                users.forEach(user => {
                    rows += `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.nome}</td>
                            <td>${user.cpf}</td>
                            <td>${user.email}</td>
                            <td>
                                <button class="btn btn-warning btn-edit" data-id="${user.id}">Edit</button>
                                <button class="btn btn-danger btn-delete" data-id="${user.id}">Delete</button>
                            </td>
                        </tr>
                    `;
                });
                $('#userTableBody').html(rows);
            }
        });
    }

    // Open modal to add user
    $('#btnAdd').click(function() {
        $('#userForm')[0].reset();
        $('#userId').val('');
        $('#userModal').modal('show');
    });

    // Open modal to edit user
    $('#userTableBody').on('click', '.btn-edit', function() {
        const userId = $(this).data('id');
        $.ajax({
            url: `${API_URL}/${userId}`,
            method: 'GET',
            success: function(user) {
                $('#userId').val(user.id);
                $('#userName').val(user.nome);
                $('#userCpf').val(user.cpf);
                $('#userEmail').val(user.email);
                $('#userModal').modal('show');
            }
        });
    });

    // Save user (create or update)
    $('#userForm').submit(function(event) {
        event.preventDefault();
        const userId = $('#userId').val();
        const user = {
            nome: $('#userName').val(),
            cpf: $('#userCpf').val(),
            email: $('#userEmail').val()
        };

        if (userId) {
            // Update user
            $.ajax({
                url: `${API_URL}/${userId}`,
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(user),
                success: function() {
                    $('#userModal').modal('hide');
                    fetchUsers();
                }
            });
        } else {
            // Create user
            $.ajax({
                url: API_URL,
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(user),
                success: function() {
                    $('#userModal').modal('hide');
                    fetchUsers();
                }
            });
        }
    });

    // Delete user
    $('#userTableBody').on('click', '.btn-delete', function() {
        const userId = $(this).data('id');
        if (confirm('Are you sure you want to delete this user?')) {
            $.ajax({
                url: `${API_URL}/${userId}`,
                method: 'DELETE',
                success: function() {
                    fetchUsers();
                }
            });
        }
    });

    // Initial fetch of users
    fetchUsers();
});
