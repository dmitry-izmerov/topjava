/**
 * Created by demi
 * on 04.11.15.
 */
function UserList(options) {

    BaseList.call(this, options);

    this.setEditForm = function($tr) {
        $('#name').val($tr.find('td:eq(0)').text());
        $('#email').val($tr.find('td:eq(1)').text());
        $('#password').val('');
        var checkbox = $tr.find('td:eq(3) input');
        $('#enabled').prop('checked', checkbox.prop('checked'));
    };

    this.clearEditForm = function() {
        $('#name').val('');
        $('#email').val('');
        $('#password').val('');
        $('#enabled').prop('checked', false);
    };

    this.createdRow = function(row, data, dataIndex) {
        var $tr = $(row);
        $tr.data('id', (data.id ? data.id : $tr.data('id')));
        $tr.find('td:eq(0)').text(data.name);

        if (data.email.includes('<a')) {
            data.email = $(data.email).text();
        }
        $tr.find('td:eq(1)').html('<a href="mailto:' + data.email + '">' + data.email + '</a>');
        $tr.find('td:eq(2)').text(data.roles);
        $tr.find('td:eq(3)').html('<input type="checkbox" ' + (data.enabled ? 'checked' : '') + '/>');

        var date = new Date(data.registered);
        $tr.find('td:eq(4)').text(moment(date).format('DD-MM-YYYY'));
        $tr.find('td:eq(5)').html('<button class="btn btn-sm btn-primary edit">Edit</button>');
        $tr.find('td:eq(6)').html('<button class="btn btn-sm btn-danger delete">Delete</button>');
    };

    this.init();
}

inherit(UserList, BaseList);