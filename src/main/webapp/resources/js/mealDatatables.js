/**
 * Created by demi
 * on 22.11.15.
 */
$(function () {
    var ajaxUrl = 'ajax/profile/meals/',
        params;

    params = {
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return date.replace('T', ' ');
                    }
                    return date;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    };

    new MealList({
        dataTableParams : params,
        ajaxUrl : ajaxUrl,
        btnAddSelector : '#add',
        inputIdSelector : '#id',
        editDialogSelector : '#editDialog',
        editFormSelector : '#editForm',
        tableSelector : '#datatable',
        btnEditSelector : '.btn-edit',
        btnDeleteSelector : '.btn-delete'
    });
});