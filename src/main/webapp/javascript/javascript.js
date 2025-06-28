var previous = {id: -1, item: 'fish'};

function checkItem(id) {
    if (previous.id !== id) {
        fetch("http://localhost:8080/Veggipe/api/items/" + id)
            .then(
                response => response.text()
            ).then(received => {
            let item = JSON.parse(received)
            previous = item
            let name = item.name
            let unit = item.unit
            const itemInfoDiv = document.getElementById('overview');

            // Tell user what they have scanned
            itemInfoDiv.innerHTML =
                "<div id=\"result\">" + name +" ("+ unit + " g/item) " + "</div>" +
                "<div id=\"counterbox\"><label for=\"counter\">Enter amount</label><input id=\"counter\" onchange=\"\"></div>" +
                "<div id=\"button\"><button onclick=\"addToCart(1)\">Add to cart</button><button onclick=\"addToCart(0)\">Cancel</button></div>" +
                "<div id=\"scanned\"></div>" +
                "<div id=\"recipe\"></div>" +
                "<div id=\"recipes\"></div>";
        }).catch(error => {
            console.error("Error fetching item information:", error);
        })
    }
}

function addToCart(type) {
    let amount
    if (type === 1) {
        amount = document.getElementById("counter").value;
    } else {
        amount = 0
    }
    if (previous && !isNaN(amount)) {
        const data = {
            item: previous,
            amount: parseFloat(amount)
        };

        fetch("http://localhost:8080/Veggipe/api/scanneditems", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }).then(
            response => response.text()
        ).then(items => {
                makeOverview(items);
            }
        );
    }
}

function makeOverview(items) {
    let list = JSON.parse(items);
    let overview = "<br><table><tr><th>name</th><th>amount</th><th>action</th></tr>";
    for (var key in list) {
        let entry = list[key];
        let row = "<tr>" +
            "<td>" + entry.item.name + "</td>" +
            "<td>" + entry.amount + "</td>" +
            "<td><button onclick=\"updateAmount(-1, " + entry.item.id + ")\">-</button><button onclick=\"updateAmount(1, " + entry.item.id + ")\">+</button></td>" +
            "</tr>";
        overview += row;
    }
    document.getElementById("result").innerHTML = "<h4>Scan an item</h4>";
    document.getElementById("counterbox").innerHTML = "";
    document.getElementById("button").innerHTML = "";
    document.getElementById("scanned").innerHTML = "Shopping cart: " + overview + "</table>";
    document.getElementById("recipe").innerHTML = "<button onclick=\"generateRecipe()\">Generate Recipe</button>";
}


function updateAmount(amount, id) {
    fetch("http://localhost:8080/Veggipe/api/scanneditems/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "text/plain"
        },
        body: amount.toString()
    }).then(
        response => response.text()
    ).then(items => {
            makeOverview(items)
        }
    );
}

function generateRecipe() {
    fetch("http://localhost:8080/Veggipe/api/recipes", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(
        response => response.text()
    ).then(recipeList => {
        makeRecipeOverview(recipeList);
        document.getElementById("recipe").innerHTML = "";
    })
        .catch(error => {
            console.error("Error generating recipes:", error);
        });
}

function makeRecipeOverview(items) {
    let recipes = JSON.parse(items);
    let overview = "<br></h4><table><tr><th>name</th><th>action</th></tr>";
    for (var key in recipes) {
        let entry = recipes[key];
        let row = "<tr>" +
            "<td>" + entry.title + "</td>" +
            "<td><button onclick=\"showRecipe(" + entry.id + ")\">Show details</button></td>" +
            "</tr>";
        overview += row;
    }
    document.getElementById("recipes").innerHTML = overview + "</table>";
}
function showRecipe(id) {
    fetch(
        "http://localhost:8080/Veggipe/api/recipes/" + id
    ).then(
        responese => responese.text()
    ).then(
        item => {
            let recipe = JSON.parse(item);
            document.getElementById("title").innerHTML = "<h4>" + recipe.title + "</h4>";
            document.getElementById("ingredients").innerHTML = processIngredients(recipe.ingredients);
            document.getElementById("instructions").innerHTML = recipe.instructions;
        }
    )
}

function processIngredients(ingredients) {
    let overview = "<table><tr><th>ingredient</th><th>amount</th></tr>";
    for (var key in ingredients) {
        let entry = ingredients[key];
        let row = "<tr>" +
            "<td>" + entry.name + "</td>" +
            "<td>" + entry.amount + "</td>" +
            "</tr>";
        overview += row;
    }
    return overview + "</table>"
}

