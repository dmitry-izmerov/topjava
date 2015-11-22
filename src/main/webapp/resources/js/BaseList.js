/**
 * Created by demi
 * on 04.11.15.
 *
 * Base class for lists
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

    this.initDataTable = function() {
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
            self.deleteRow($tr.data('id'));
        });

        $(this.editFormSelector).submit(function () {
            self.saveRow();
            return false;
        });

        /**
         * Add ajax handler for all document
         */
        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            self.failNoty(event, jqXHR, options, jsExc);
        });

        // for csrf
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    };

    this.refreshRow = function (id) {
        var $form = $(this.editFormSelector),
            self = this;

        $.get(this.ajaxUrl + id, function (data) {
            $.each(data, function (key, value) {
                $form.find("input[name='" + key + "']").val(value);
            });
            $(self.editDialogSelector).modal();
        });
    };

    this.saveRow = function () {
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

    this.deleteRow = function (id) {
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
            self.updateTableByData(data);
        });
    };

    this.updateTableByData = function(data) {
        this.dataTable.clear().rows.add(data).draw();
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
        var errorInfo = $.parseJSON(jqXHR.responseText);

        closeNote();
        failedNote = noty({
            text: 'Failed: ' + jqXHR.statusText + "<br>" + errorInfo.cause + "<br>" + errorInfo.detail,
            type: 'error',
            layout: 'bottomRight'
        });
    };

    this.renderEditBtn = function (data, type, row) {
        if (type == 'display') {
            return '<a class="btn btn-sm btn-primary btn-edit">Edit</a>';
        }
        return data;
    };

    this.renderDeleteBtn = function (data, type, row) {
        if (type == 'display') {
            return '<a class="btn btn-sm btn-danger btn-delete">Delete</a>';
        }
        return data;
    };

    this.initFilter = function () {
    };

    this.initAdditions = function() {
    };

    this.init = function() {
        this.initDataTable();
        this.makeEditable();
        this.initFilter();
        this.initAdditions();
    };
}