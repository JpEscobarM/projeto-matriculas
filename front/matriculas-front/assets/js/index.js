const divConteudo = document.querySelector('.div-mostra-conteudo');
let divConteudoExibido = null;
let formularioDeDados = null;
let listaEstados = null;
let botaoBuscar = null;
let selectEstado = null;
let selectModalidade = null;
let divResultadoBusca = null;



function removeElemento(elemento)
{
    if(elemento !== null)
    {
        elemento.remove();
    }

}
function criaDiv(elementoPai)
{
    removeElemento(divConteudoExibido);

    divConteudoExibido = document.createElement("div");
    divConteudoExibido.classList.add('div-conteudo');

    elementoPai.appendChild(divConteudoExibido);


}


async function criaSelects() {
    selectModalidade = document.createElement('select');
    selectModalidade.classList.add("select-modalidade");

    selectModalidade.innerHTML = `<option value="ALL">Todas as modalidades</option>
                                   <option value="EAD">EAD</option>
                                   <option value="PRESENCIAL">Presencial</option>
    `;

    selectEstado = document.createElement('select');
    selectEstado.classList.add("select-estado");
    
    selectEstado.innerHTML = `<option value="ALL">Todos os Estados</option>
    `;

    try{
        const response = await fetch('http://localhost:8080/matriculas/estados');
        const estados = await response.json();

        estados.forEach(estado => {
                const option = document.createElement("option");
                option.value =estado;
                option.innerText=estado;
                selectEstado.appendChild(option); 
        });

    }catch(error)
    {
        console.error("Erro ao buscar estados no endpooint: ",error);
    }

}

 function criaMatriculasPorAno(div) 
{
     
    div.appendChild(selectModalidade);
    div.appendChild(selectEstado);

    
    botaoBuscar = document.createElement("button");
    botaoBuscar.classList.add("btn-buscar");
    botaoBuscar.innerText="Buscar";
    div.appendChild(botaoBuscar);

    divResultadoBusca = document.createElement('div');
    divResultadoBusca.classList.add('resultado');

    div.appendChild(divResultadoBusca);


}

async function buscaTotalPorAno(param1,param2)
{
  

    //PARAM1 = MODALIDADE E PARAM2 = ESTADO
    if(param1=== "ALL" && param2 ==="ALL")
    {
        console.log("SEM FILTRO ");
        try{
            const response = await fetch('http://localhost:8080/matriculas/total-por-ano');
            const matriculasPorAno= await response.json();
            const mapMatriculas = new Map(Object.entries(matriculasPorAno));

        return mapMatriculas;

        }catch(erro)
        {
            console.log("Erro ao puxar dados sem filtro",erro);
        }
    }
    else if( param1 !== 'ALL' && param2 ==="ALL")
    {
        console.log("FILTRO DE MODALIDADE");
        try{    

            const response = await fetch(`http://localhost:8080/matriculas/total-por-ano?modalidade=${param1}`);
            const matriculasPorAno= await response.json();
            const mapMatriculas = new Map(Object.entries(matriculasPorAno));
            
        return mapMatriculas;

        }catch(erro)
        {
            console.log("Erro ao puxar dados sem filtro",erro);
        }
    }
    else if( param1 === 'ALL' && param2 !=="ALL")
        {
            console.log("FILTRO DE ESTADO");
            try{    
    
                const response = await fetch(`http://localhost:8080/matriculas/total-por-ano?estado=${param2}`);
                const matriculasPorAno= await response.json();
                const mapMatriculas = new Map(Object.entries(matriculasPorAno));
                
            return mapMatriculas;
    
            }catch(erro)
            {
                console.log("Erro ao puxar dados sem filtro",erro);
            }
        }
        else if( param1 !== 'ALL' && param2 !=="ALL")
            {
                console.log("FITLRO DE MODALIDADE E ESTADO");
                try{    
        
                    const response = await fetch(`http://localhost:8080/matriculas/total-por-estado?estado=${param2}&modalidade=${param1}`);
                    const matriculasPorAno= await response.json();
                    const mapMatriculas = new Map(Object.entries(matriculasPorAno));
                    
                return mapMatriculas;
        
                }catch(erro)
                {
                    console.log("Erro ao puxar dados sem filtro",erro);
                }
            }

};

function criaTabelaPorMap(map)
{

    const table = document.createElement('table');
    table.classList.add('tabela-matriculas');
    const thead = document.createElement("thead");
    thead.innerHTML =`<tr>
    <th>Ano</th>
    <th>Total de Matrículas</th>
    </tr>
    `;

    table.appendChild(thead);
    let tbody = document.createElement("tbody");


    map.forEach((valor,chave) =>
    {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${chave}</td>
            <td>${valor}</td>
        `
        tbody.appendChild(tr);
    }
);

   table.appendChild(tbody);

   

    divResultadoBusca.appendChild(table);
}

criaSelects();

document.addEventListener('click', async function(event)
{

    let elementoClick = event.target;
    console.log(elementoClick);

    if(elementoClick.classList.contains('btn-matriculas-por-ano'))
    {
      
      criaDiv(divConteudo);
      criaMatriculasPorAno(divConteudoExibido);

    }
    else if(elementoClick.classList.contains("btn-buscar"))
    {
        console.log("Modalidade:", selectModalidade.value);
        console.log("Estado:", selectEstado.value);
        divResultadoBusca.innerHTML ='';
       const map = await buscaTotalPorAno(selectModalidade.value,selectEstado.value);
      
      
       criaTabelaPorMap(map);

    }

}
);