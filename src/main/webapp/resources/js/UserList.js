/**
 * Created by demi
 * on 04.11.15.
 */
function UserList(options) {

    BaseList.call(this, options);

    this.createdRow = function (row, data, dataIndex) {
        if (!data.enabled) {
            $(row).css("text-decoration", "line-through");
        }
        $(row).data('id', data.id);
    };

    this.enable = function ($chkbox, id) {
        var self = this,
            enabled = $chkbox.is(":checked");

        $chkbox.closest('tr').css("text-decoration", enabled ? "none" : "line-through");
        $.ajax({
            url: self.ajaxUrl + id,
            type: 'POST',
            data: 'enabled=' + enabled,
            success: function () {
                self.successNoty(enabled ? 'Enabled' : 'Disabled');
            }
        });
    };

    this.initHandlers = function () {
        var self = this;

        $(this.tableSelector).on('click', 'input[type="checkbox"]', function () {
            var $tr = $(this).closest('tr');
            self.enable($(this), $tr.data('id'));
        });
    };

    this.initAdditions = function() {
        this.initHandlers();
    };

    this.init();
}

inherit(UserList, BaseList);