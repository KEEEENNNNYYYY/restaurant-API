# TD-PROG3

Working end point :


## 1-
 *Return all dishes inside the table 'dish' inside the **Database :***

    GET : /api/dishes/all

ex :

    {
    "id_dish": "1",
    "name": "Hot Dog",
    "unit_price": 15000
    }, 

## 2-
*Return a specific dish with all details about it*
    
    GET : /api/dishes/{id}

ex :

    "id_dish": "1",
    "name": "Hot Dog",
    "unit_price": 15000,
    "dishIngredient":
    {
      "id": null,
      "id_ingredient": "1",
      "quantity": 100,
      "ingredient": {
        "id": "1",
        "name": "Saucisse",
        "prices": 20,
        "unit": "G",
        "updatedOn": "2025-03-27",
        "stock": null
      },
      "dishId": null,
      "ingredientPrice": 2000
    },

## **This one still need fix on some null value**

# 3-
*Return a list of all ingredient :* 

    GET : /api/Ingredient/all

ex : 

    {
      "id": "1",
      "name": "Saucisse",
      "updatedAt": "2025-01-01",
      "unitPrice": 20
    },
    {
      "id": "2",
      "name": "Huile",
      "updatedAt": "2025-01-01",
      "unitPrice": 10000
    },
    {
      "id": "3",
      "name": "Oeuf",
      "updatedAt": "2025-01-01",
      "unitPrice": 1000
    },

# 4- 

*Return the ingredient matching the id*

    GET : /api/Ingredient/{id}

ex : 

    {
      "id": 1,
      "name": "Saucisse",
      "updatedAt": "2025-01-01",
      "unitPrice": 20
    }

# 5-

*Return a filtered list of ingredient :*

    GET : /api/Ingredient?priceMinFilter={value}

    GET : /api/Ingredient?priceMaxFilter={value}

    /Ingredient?priceMinFilter={value}&priceMaxFilter={value}

ex: 

    [
      {
        "id": 3,
        "name": "Oeuf",
        "updatedAt": "2025-01-01",
        "unitPrice": 1000
      },
      {
        "id": 4,
        "name": "Pain",
        "updatedAt": "2025-01-01",
        "unitPrice": 1000
      }
    ]

# 6- 

*Update a Create a new Ingredient*

    Create : 
    POST : /api/Ingredient

    Update : 
    PUT : /api/Ingredient

