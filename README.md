Upload and process input file API 
POST request to : localhost:8080/upload
with multipart file in body request. 

reponse as shown below. 
{
    "message": "Input file processed successfully",
    "result": {
        "Failed_Coz_Of_Missing_Mandatory_Fields": 1,
        "Inserted_Books": 3,
        "Inserted_Authors": 6,
        "Failed_Publications_Coz_There_Is_No_Author": 0,
        "Inserted_Magazines": 4
    },
    "status": 200,
    "timeStamp": "2021-06-15 00:57:07"
}



GET APIS. 
Get all publications. 
get requst to : http://localhost:8080/publication

sample from Response as shown below. 

[
    {
        "title": "Sketching the Future",
        "pType": "B",
        "id": 1,
        "authors": [
            {
                "email": "jan@scope.com",
                "firstName": "Jan",
                "lastName": "Novak",
                "id": 1
            },
            {
                "email": "maha@scope.com",
                "firstName": "Maha",
                "lastName": "Malika",
                "id": 3
            },
            {
                "email": "marcus@scope.com",
                "firstName": "Marcus",
                "lastName": "Keiser",
                "id": 4
            },
            {
                "email": "rory@scope.com",
                "firstName": "Rory",
                "lastName": "Dorestad",
                "id": 2
            },
            {
                "email": "perica@scope.com",
                "firstName": "Perica",
                "lastName": "Mudar",
                "id": 6
            },
            {
                "email": "bereshad@scope.com",
                "firstName": "Bereshad",
                "lastName": "Schmied",
                "id": 5
            }
        ],
        "description": "Key principles to make your project a success",
        "isbn": "278-1-56619-909-4"
    },
    {
        "title": "100+1 Healthy Recipes",
        "pType": "B",
        "id": 7,
        "authors": [
            {
                "email": "perica@scope.com",
                "firstName": "Perica",
                "lastName": "Mudar",
                "id": 6
            },
            {
                "email": "bereshad@scope.com",
                "firstName": "Bereshad",
                "lastName": "Schmied",
                "id": 5
            }
        ],
        "description": "\"recipes for mains, sides, appetizers and snacks\"",
        "isbn": "478-1-56619-909-4"
    }
]


Get publication by ISBN. 
Get request to : http://localhost:8080/publication/isbn/{ISBN} 
sample request : http://localhost:8080/publication/isbn/478-1-56619-909-4

sample response as shown below. 
{
    "title": "100+1 Healthy Recipes",
    "pType": "B",
    "id": 7,
    "authors": [
        {
            "email": "perica@scope.com",
            "firstName": "Perica",
            "lastName": "Mudar",
            "id": 6
        },
        {
            "email": "bereshad@scope.com",
            "firstName": "Bereshad",
            "lastName": "Schmied",
            "id": 5
        }
    ],
    "description": "\"recipes for mains, sides, appetizers and snacks\"",
    "isbn": "478-1-56619-909-4"
}



Get publication by Author's mail. 
Get request to : http://localhost:8080/publication/mail/{mail}
sample request : http://localhost:8080/publication/mail/bereshad@scope.com
sample response as shown below. 
[
    {
        "title": "Sketching the Future",
        "pType": "B",
        "id": 1,
        "authors": [
            {
                "email": "bereshad@scope.com",
                "firstName": "Bereshad",
                "lastName": "Schmied",
                "id": 5
            },
            {
                "email": "rory@scope.com",
                "firstName": "Rory",
                "lastName": "Dorestad",
                "id": 2
            },
            {
                "email": "jan@scope.com",
                "firstName": "Jan",
                "lastName": "Novak",
                "id": 1
            },
            {
                "email": "marcus@scope.com",
                "firstName": "Marcus",
                "lastName": "Keiser",
                "id": 4
            },
            {
                "email": "maha@scope.com",
                "firstName": "Maha",
                "lastName": "Malika",
                "id": 3
            },
            {
                "email": "perica@scope.com",
                "firstName": "Perica",
                "lastName": "Mudar",
                "id": 6
            }
        ],
        "description": "Key principles to make your project a success",
        "isbn": "278-1-56619-909-4"
    },
    {
        "title": "100+1 Healthy Recipes",
        "pType": "B",
        "id": 7,
        "authors": [
            {
                "email": "bereshad@scope.com",
                "firstName": "Bereshad",
                "lastName": "Schmied",
                "id": 5
            },
            {
                "email": "perica@scope.com",
                "firstName": "Perica",
                "lastName": "Mudar",
                "id": 6
            }
        ],
        "description": "\"recipes for mains, sides, appetizers and snacks\"",
        "isbn": "478-1-56619-909-4"
    }
]
