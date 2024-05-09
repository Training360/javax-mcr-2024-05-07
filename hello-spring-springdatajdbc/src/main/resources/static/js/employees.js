window.onload = function() {
    console.log("Hello JavaScript!");

    downloadData();

    const button = document.querySelector("#save-button");
    button.onclick = function () {
        console.log("Hello Button!");

        const input = document.querySelector("#name-input");
        const name = input.value;

        console.log("Name: " + name);

        const data = {"name": name};

        console.log("Data: ", data);

        const json = JSON.stringify(data);

        console.log("JSON: " + json);

        fetch("/api/employees", {method: "POST", headers: {
                "Content-Type": "application/json"
            }, body: json})
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                console.log("Get: " + data);
                downloadData();
            })
    }
}

function downloadData() {
    fetch("/api/employees")
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);

            const table = document.querySelector("#employees-table");
            table.innerHTML = "";
            for (let employee of data) {
                table.innerHTML += `<tr><td>${employee.id}</td><td>${employee.name}</td></tr>`;
            }
        })
}