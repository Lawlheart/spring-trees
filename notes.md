Family Tree JSON = {
    id: Integer,
    creatorId: Integer,
    treeName: String,
    people: [{
        id: Integer,
        firstName: String,
        lastName: String,
        gender: String,
        dateOfBirth: String,
        dateOfDeath: String
    } ... ],
    links: [{
        id
        treeId
        person1Id
        person2Id
        relationshipType
    }]
}