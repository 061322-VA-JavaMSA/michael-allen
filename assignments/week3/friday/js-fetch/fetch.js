document.getElementById('getData').addEventListener("click", getData);

let baseApiURL = 'https://pokeapi.co/api/v2/pokemon';

async function getData() {

    let id = document.getElementById('dataInput').value;
    let httpResponse = await fetch(`${baseApiURL}/${id}`);

    if(httpResponse.status >= 200 && httpResponse.status < 300){
        let data = await httpResponse.json();
        populateData(data);
    }
    else {
        console.log('Invalid request.');
    }
}

function populateData(response) {
    
    let data = document.getElementById('data');
    let pokemonName = response.name.charAt(0).toUpperCase() + response.name.slice(1);

    data.innerHTML = `
    <p>The Pokemon you selected was: <strong>${pokemonName}</strong>!</p>
    <ul>
        <li>Type: ${response.types[0].type['name']}</li>
        <li>Abilities: ${response.abilities[0].ability['name']}, ${response.abilities[1].ability['name']}</li>
        <li>Moves: ${response.moves[0].move['name']}, ${response.moves[1].move['name']}, ${response.moves[2].move['name']}</li>
        <li>Stats:</li>
        <ul>
            <li>${response.stats[0].stat['name']}: ${response.stats[0].base_stat}</li>
            <li>${response.stats[1].stat['name']}: ${response.stats[1].base_stat}</li>
            <li>${response.stats[2].stat['name']}: ${response.stats[2].base_stat}</li>
        </ul>
    </ul>
    `;
    
}