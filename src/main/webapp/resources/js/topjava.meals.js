const mealAjaxUrl = "ui/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

$(function () {
    makeEditable({
            ctx,
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
    makeFilter();
    makeResetFilter();
});

function makeFilter(){
    $("#buttonFilter").click(function(){
        $.ajax({
            type: "GET",
            url: ctx.ajaxUrl + "getBetween",
            data: $('#filterForm').serialize()
        }).done(function () {
            updateTable();
            successNoty("Filtered");
        });
    });
}

function makeResetFilter(){
    $("#resetFilter").click(function(){
        $("#filterForm").each(function(){
            $(this).find(":input").val("");
        });
        $("#buttonFilter").click();
    });
}
