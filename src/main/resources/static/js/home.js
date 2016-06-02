var domain = window.location.href.split("/").slice(0, 3).join("/");

function newTree(treeName, name, gender) {
    var person = {
        name: name,
        gender: gender
    };
    $.ajax({
        type: 'POST',
        url: '/api/people',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(person),
        dataType: 'json',
        success: function(person) {
            var tree = {
                name: treeName,
                creatorId: 1337,
                first: domain + "/api/people/" + person.id,
                people: [domain + "/api/people/" + person.id]
            };

            $.ajax({
                type: 'POST',
                url: '/api/trees',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(tree),
                dataType: 'json',
                success: function(tree) {
                    window.location = domain + "/display/" + tree.id;
                }
            });
        }
    });
}


$(document).ready(function() {
    $('.modal-trigger').leanModal();
    $('select').material_select();
});

$('body').on('click', '#new-tree-submit', function(e) {
    treeName = $("#treeName").val();
    name = $("#name").val();
    gender = $("#gender").val();
    console.log(treeName, name, gender);
    newTree(treeName, name, gender);

});