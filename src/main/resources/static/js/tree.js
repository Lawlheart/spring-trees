function getPersonByName(people, name) {
    for(var i=0; i<people.length; i++) {
        if(people[i].name === name) {
            return people[i];
        }
    }
    return null;
}

function newPerson(name, gender, callback) {
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
            if(callback) {
                return callback(person);
            }
            return person;
        }
    });
}



function updatePerson(id, person) {
    $.ajax({
        type: 'PUT',
        url: '/api/people/' + id,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(person),
        dataType: 'json',
        success: function(person) {
            console.log(person);
            return person;
        }
    });
}

function updateTreeList(tree, person) {
    $.ajax({
        type: 'GET',
        url: '/api/trees/' + tree.id + "/people",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        success: function (people) {
            var update = $.extend({}, tree);
            var newPeopleList = people.content.slice();
            newPeopleList.push(person);
            update.people = newPeopleList.map(function(p) {
                console.log(p);
                return domain + "/api/people/" + p.id;
            });
            update.first = domain + "/api/people/" + tree.first.id;
            $.ajax({
                type: 'PUT',
                url: '/api/trees/' + tree.id,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(update),
                dataType: 'json',
                success: function (updatedTree) {
                    tree.people.push(person);
                    location.reload();
                }
            });
        }
    });
}

function newMarriage(person, spouse, childrenIds) {
    if(childrenIds === undefined) childrenIds = [];
    var marriage = {
        personId: person.id,
        spouse: domain + "/api/people/" + spouse.id,
        children: childrenIds.map(function(childId) {
            return domain + "/api/people/" + childId;
        })
    };
    console.log(marriage);
    $.ajax({
        type: 'POST',
        url: '/api/marriages',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(marriage),
        dataType: 'json',
        success: function(marriage) {
            console.log(marriage);
            person.marriages.push(domain + "/api/marriages/" + marriage.id);
            updatePerson(person.id, person);
            updateTreeList(tree, spouse);
            return marriage;
        }
    })
}

var url = window.location.href;
var domain = window.location.href.split("/").slice(0, 3).join("/");
console.log(domain);
var people, tree;
var treeId = location.pathname.split("/").pop();
$(document).ready(function() {
    $('select').material_select();
    $.getJSON("/data/trees/" + treeId, function(data) {
        tree = data;
        people = data.people;
        console.log(data);
        var config = {
            height: 600,
            width: 800,
            nodeWidth: 100,
            callbacks: {
                textRenderer: function(name, extra, textClass) {
                    return "<p align='center' class='" + textClass + "'>" + name + "</p><button class='add-marriage cyan'>+</button><p class='tooltip'>Add Marriage</p>";
                }
            }
        };
        dTree.init([data.first], config);
    });
});
var personName, person;
$('body').on('mouseenter', '.node', function(e) {
   $(e.target).children("button").show();
}).on('mouseleave', '.node', function(e) {
    $(e.target).children("button").hide();
}).on('mouseenter', '.add-marriage', function(e) {
    $(e.target).next(".tooltip").show();
}).on('mouseleave', '.add-marriage', function(e) {
    $(e.target).next(".tooltip").hide();
}).on('click', '.add-marriage', function(e) {
    personName = $(e.target).prev().html();
    person = getPersonByName(people, personName);
    $('#related-person').text(personName);
    $('#addMarriageModal').openModal();
}).on('click', '.add-confirm', function(e) {
    newPerson($("#spouseName").val(), $("#spouseGender").val(), function(spouse) {
        newMarriage(person, spouse);
    });
});