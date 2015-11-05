/**
 * Created by demi
 * on 04.11.15.
 */
function BaseList(options) {
    this.options = options;
    this.failedNote = null;
    this.ajaxUrl = options.ajaxUrl;
    this.btnAddSelector = options.btnAddSelector;
    this.inputIdSelector = options.inputIdSelector;
    this.editDialogSelector = options.editDialogSelector;
    this.editFormSelector = options.editFormSelector;
    this.tableSelector = options.tableSelector;
    this.btnEditSelector = options.btnEditSelector;
    this.btnDeleteSelector = options.btnDeleteSelector;
    this.dataTableParams = options.dataTableParams;

    this.setEditForm = function($tr) {
        throw new Error('You must override function setEditForm!');
    };

    this.clearEditForm = function() {
        throw new Error('You must override function clearEditForm!');
    };

    this.createdRow = function(row, data, dataIndex) {
        throw new Error('You must override function createdRow!');
    };

    this.initDataTable = function() {
        this.dataTableParams['createdRow'] = this.createdRow;
        this.dataTable = $(this.tableSelector).DataTable(this.dataTableParams);
    };

    this.makeEditable = function() {

        var self = this;

        $(this.btnAddSelector).click(function () {
            $(self.inputIdSelector).val(0);
            self.clearEditForm();
            $(self.editDialogSelector).modal();
        });

        $(this.tableSelector).on('click', this.btnEditSelector, function () {
            var $tr = $(this).closest('tr');

            $(self.inputIdSelector).val($tr.data('id'));
            self.setEditForm($tr);
            $(self.editDialogSelector).modal();
        });

        $(this.tableSelector).on('click', this.btnDeleteSelector, function () {
            var $tr = $(this).closest('tr');
            self.deleteItem($tr.data('id'));
        });

        $(this.editFormSelector).submit(function () {
            self.saveItem();
            return false;
        });

        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            self.failNoty(event, jqXHR, options, jsExc);
        });
    };

    this.deleteItem = function (id) {
        var self = this;

        $.ajax({
            url: this.ajaxUrl + id,
            type: 'DELETE',
            success: function () {
                self.updateTable();
                self.successNoty('Deleted');
            }
        });
    };

    this.updateTable = function () {
        var self = this;

        $.get(this.ajaxUrl, function (data) {
            self.updateData(data);
        });
    };

    this.updateData = function(data) {
        var self = this;
        this.dataTable.clear();
        $.each(data, function (key, item) {
            self.dataTable.row.add(item);
        });
        this.dataTable.draw();
    };

    this.saveItem = function () {
        var $form = $(this.editFormSelector),
            self = this;

        $.ajax({
            type: "POST",
            url: this.ajaxUrl,
            data: $form.serialize(),
            success: function () {
                $(self.editDialogSelector).modal('hide');
                self.updateTable();
                self.successNoty('Saved');
            }
        });
    };

    this.closeNote = function () {
        if (this.failedNote) {
            this.failedNote.close();
            this.failedNote = undefined;
        }
    };

    this.successNoty = function (text) {
        this.closeNote();
        noty({
            text: text,
            type: 'success',
            layout: 'bottomRight',
            timeout: true
        });
    };

    this.failNoty = function (event, jqXHR, options, jsExc) {
        this.closeNote();
        this.failedNote = noty({
            text: 'Failed: ' + jqXHR.statusText + "<br>",
            type: 'error',
            layout: 'bottomRight'
        });
    };

    this.initFilter = function () {
    };

    this.init = function() {
        this.initDataTable();
        this.makeEditable();
        this.initFilter();
    };
}