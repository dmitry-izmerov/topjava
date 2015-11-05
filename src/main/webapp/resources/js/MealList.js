/**
 * Created by demi
 * on 04.11.15.
 */
function MealList(options) {

    BaseList.call(this, options);

    this.setEditForm = function($tr) {
        $('#dateTime').val(moment(new Date(parseInt($tr.data('date-time')))).format('YYYY-MM-DD[T]HH:mm'));
        $('#description').val($tr.find('td:eq(1)').text());
        $('#calories').val($tr.find('td:eq(2)').text());
    };

    this.clearEditForm = function() {
        $('#dateTime').val('');
        $('#description').val('');
        $('#calories').val('');
    };

    this.createdRow = function(row, data, dataIndex) {
        var $tr = $(row);
        $tr.data('id', (data.id ? data.id : $tr.data('id')));
        $tr.data('date-time', +new Date(data.dateTime));
        $tr.find('td:eq(0)').text(data.dateTime);
        $tr.find('td:eq(1)').text(data.description);
        $tr.find('td:eq(2)').text(data.calories);
        $tr.find('td:eq(3)').html('<button class="btn btn-sm btn-primary btn-edit">Edit</button>');
        $tr.find('td:eq(4)').html('<button class="btn btn-sm btn-danger btn-delete">Delete</button>');
    };

    this.initFilter = function () {
        var self = this;

        $('#filterForm').submit(function () {
            $.ajax({
                url : self.ajaxUrl + 'filter',
                method : 'GET',
                data : $(this).serialize(),
                success: function (data) {
                    self.updateData(data);
                    self.successNoty('Filtered');
                }
            });
            return false;
        });
    };

    this.init();
}

inherit(MealList, BaseList);