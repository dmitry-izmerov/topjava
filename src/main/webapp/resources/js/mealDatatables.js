/**
 * Created by demi
 * on 10.11.15.
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
                        debugger;
                        var dateObject = new Date(date);
                        return '<span>' + dateObject.toISOString().substring(0, 10) + '</span>';
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
        btnAddSelector : '#btnAdd',
        inputIdSelector : '#id',
        editDialogSelector : '#editDialog',
        editFormSelector : '#editForm',
        tableSelector : '#mealsTable',
        btnEditSelector : '.btn-edit',
        btnDeleteSelector : '.btn-delete'
    });
});