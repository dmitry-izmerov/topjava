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
        $tr.data('id', data.id);
        $tr.removeClass('normal').removeClass('exceeded');
        $tr.addClass(data.exceed ? 'exceeded' : 'normal');
    };

    this.initFilter = function () {
        var self = this;

        $('#filterForm').submit(function () {
            $.ajax({
                url : self.ajaxUrl + 'filter',
                method : 'GET',
                data : $(this).serialize(),
                success: function (data) {
                    self.updateTableByData(data);
                    self.successNoty('Filtered');
                }
            });
            return false;
        });
    };

    this.init();
}

inherit(MealList, BaseList);