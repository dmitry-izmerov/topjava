/**
 * Created by demi
 * on 04.11.15.
 */
function MealList(options) {

    BaseList.call(this, options);

    this.createdRow = function(row, data, dataIndex) {
        var $tr = $(row);
        $tr.data('id', data.id);
        $tr.removeClass('normal').removeClass('exceeded');
        $tr.addClass(data.exceed ? 'exceeded' : 'normal');
    };

    this.updateTable = function () {
        var self = this;

        $.ajax({
            type: "POST",
            url: this.ajaxUrl + 'filter',
            data: $('#filter').serialize(),
            success: function (data) {
                self.updateTableByData(data);
            }
        });
        return false;
    };

    this.initFilter = function () {
        var self = this;

        $('#filter').submit(function () {
            self.updateTable();
            return false;
        });
    };

    this.initTimePicker = function() {

        var startDate = $('#startDate'),
            endDate = $('#endDate');

        startDate.datetimepicker({
            timepicker: false,
            format: 'Y-m-d',
            lang: 'ru',
            formatDate: 'Y-m-d',
            onShow: function (ct) {
                this.setOptions({
                    maxDate: endDate.val() ? endDate.val() : false
                })
            }
        });

        endDate.datetimepicker({
            timepicker: false,
            format: 'Y-m-d',
            lang: 'ru',
            formatDate: 'Y-m-d',
            onShow: function (ct) {
                this.setOptions({
                    minDate: startDate.val() ? startDate.val() : false
                })
            }
        });

        $('.time-picker').datetimepicker({
            datepicker: false,
            format: 'H:i',
            lang: 'ru'
        });

        $('#dateTime').datetimepicker({
            format: 'Y-m-d\\TH:i',
            lang: 'ru'
        });
    };

    this.initAdditions = function() {
        this.initFilter();
        this.initTimePicker();
    };

    this.init();
}

inherit(MealList, BaseList);