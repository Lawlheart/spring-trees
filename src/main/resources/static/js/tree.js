function getPersonById(people, id) {
    for(var i=0; i<people.length; i++) {
        if(people[i].id === id) {
            return people[i];
        }
    }
    return null;
}

function isEmptyMarriage(marriage) {
    return marriage === undefined
        || marriage.length === 0
        || ($.isEmptyObject(marriage[0].spouse)
        && marriage[0].children.length === 0);
}

function getMarriages(people, person, generation) {
    if(generation > 2) return;
    var spouse = {};
    var children = [];
    if(person.marriages.length > 0 && typeof person.marriages[0] === "number" ) {
        var spouses = person.marriages.map(function(id) {
            var spouse = getPersonById(people, id);
            delete spouse.marriages;
            delete spouse.children;
            var partnerIndex = $.inArray(person, spouse);
            if(partnerIndex >= 0) {
                spouse.marriages = getMarriages(people.slice().splice(partnerIndex, 1), spouse, generation + 1);
            }
            return spouse;
        });
        spouse = spouses[0];
    }
    if(person.children.length > 0) {
        children = person.children.map(function(id) {
            var child = getPersonById(people, id);
            var m = getMarriages(people, child, generation + 1);
            child.marriages = m;
            if(isEmptyMarriage(m)) delete child.marriages;
            delete child.children;
            return child;
        });
    }
    return [{ spouse: spouse,  children: children }];
}

function convertTree(data) {
    var people = data.people.map(function(person) {
        person.class = "node pid-" + person.id;
        person.class += (person.gender === "Female") ? " woman " : " man";
        return person;
    });
    var head = data.people[0];
    return [{
        name: head.name,
        class: head.class,
        textClass: "",
        depthOffset: 0,
        marriages: getMarriages(people, head, 1),
        extra: {}
    }];
}

function markUnknown(name, extra, textClass) {
    if(name === undefined) name = "Unknown";
    return "<p align='center' class='" + textClass + "'>" + name + "</p><button class='add-person cyan'>+</button>";
}

var relativeId, relativeName;
var uri = location.pathname.replace("display", "trees");
$(document).ready(function() {
    $('select').material_select();
    console.log(uri);
    $.getJSON("/data" + uri, function(data) {
        var tree = convertTree(data);
        var config = {
            height: 600,
            width: 800,
            nodeWidth: 100,
            callbacks: {
                textRenderer: markUnknown
            }
        };
        dTree.init(tree, config);
    });
});

$('body').on('mouseenter', '.node', function(e) {
   var $button = $(e.target).children("button");
    $button.show();
}).on('mouseleave', '.node', function(e) {
    var $button = $(e.target).children("button");
    $button.hide();
}).on('click', '.add-person', function(e) {
    relativeId = $(e.target).parent()[0].className.replace(/\D/g, "");
    relativeName = $(e.target).prev().html();
    $('#related-person').text(relativeName);
    $('#addPersonModal').openModal();
}).on('click', '.add-confirm', function(e) {
    var newPerson = {
        "name": $('#name').val(),
        "gender": $('#gender').val()
    };
    console.log(newPerson);
    $.post('/people', newPerson, function(data) {
        console.log(data);
    });
});