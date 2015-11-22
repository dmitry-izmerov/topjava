/**
 * Created by demi
 * on 04.11.15.
 */
function MealList(options) {

    BaseList.call(this, options);

    this.setEditForm = function($tr) {
        $('#dateTime').val($tr.data('date-time'));
        $('#description').val($tr.find('td:eq(1)').text());
        $('#calories').val($tr.find('td:eq(2)').text());
    };

    this.clearEditForm = function() {
        $('#dateTime').val('');
        $('#description').val('');
        $('#calories').val('');
    };

    // remove ?
    this.createdRow = function(row, data, dataIndex) {
        var $tr = $(row);
        $tr.data('id', data.id);
        $tr.data('date-time', moment(new Date(data.dateTime)).format('YYYY-MM-DD[T]HH:mm'));
        $tr.removeClass('normal').removeClass('exceeded');
        $tr.addClass(data.exceed ? 'exceeded' : 'normal');
    };

    this.initFilter = function () {
        var self = this;

        $('#filterForm').submit(function () {
            $.ajax({
                url : self.ajaxUrl + 'filter',
                method : 'POST',
                data : $(this).serialize(),
                success: function (data) {
                    self.updateTableByData(data);
                    self.successNoty('Filtered');
                }
            });
            return false;
        });
    };

    this.initTimePicker = function() {
        // filter
        $('#startDate').datetimepicker({
            timepicker:false,
            format:'YYYY-MM-DD',
            formatDate:'YYYY-MM-DD'
        });
        $('#endDate').datetimepicker({
            timepicker:false,
            format:'YYYY-MM-DD',
            formatDate:'YYYY-MM-DD'
        });
        $('#startTime').datetimepicker({
            datepicker:false,
            format:'HH:mm',
            formatTime:'HH:mm'
        });
        $('#endTime').datetimepicker({
            datepicker:false,
            format:'HH:mm',
            formatTime:'HH:mm'
        });

        // edit form
        $('#dateTime').datetimepicker({
            format:'YYYY-MM-DD[T]HH:mm',
            formatTime:'HH:mm',
            formatDate:'YYYY-MM-DD'
        });
    };

    this.initAdditions = function() {
        this.initTimePicker();
    };

    this.init();
}

inherit(MealList, BaseList);