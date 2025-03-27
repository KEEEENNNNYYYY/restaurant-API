# TD-PROG3

Working end point :


## 1-
 *Return all dishes inside the table 'dish' inside the **Database :***

    /api/dishes/all

ex :

    {
    "id_dish": "1",
    "name": "Hot Dog",
    "unit_price": 15000
    }, 

## 2-
*Return a specific dish with all details about it*
    
    /api/dishes/{id}

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
    