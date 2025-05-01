const divConteudo = document.querySelector('.div-mostra-conteudo');

let divConteudoExibido = null;
let formularioDeDados = null;
let listaEstados = null;
let botaoBuscar = null;
let selectEstado = null;
let selectModalidade = null;
let divResultadoBusca = null;
let historicoRequisicoes = JSON.parse(localStorage.getItem('historicoRequisicoes')) || [];

renderizaHistorico();

function renderizaHistorico()
{
    let divHistorico = document.querySelector(".historico-requisicoes");

    if(!divHistorico)
    {
        divHistorico = document.createElement("div");
        divHistorico.classList.add("historico-requisicoes");
        divConteudo.appendChild(divHistorico);
    }

    divHistorico.innerHTML = "<h3>Últimas Consultas:</h3>";

   

    historicoRequisicoes.forEach(req => {
        
        
        const btnHistorico = document.createElement("button");
       

        btnHistorico.innerText = `${ req.tipo.toUpperCase()} | ${req.modalidade} | ${req.estado}`
        
        btnHistorico.classList.add("btn-historico");
        
        btnHistorico.addEventListener('click', async () => {
            if (req.tipo === "ano") {
                const map = await buscaTotalPorAno(req.modalidade, req.estado);
                divResultadoBusca.innerHTML = "";
                criaTabelaPorMap(map,"Ano");
            } else if (req.tipo === "ranking") {
                const map = await buscaRanking(req.modalidade, req.estado);
                divResultadoBusca.innerHTML = "";
                criaTabelaPorMap(map,"Cursos");
            }
        });

        divHistorico.appendChild(btnHistorico);

    });

  

}

function registraRequisicao(tipo,modalidade,estado){
    const novaConsulta =    { tipo,modalidade,estado};

    historicoRequisicoes = historicoRequisicoes.filter(
        item => !(item.tipo === tipo && item.modalidade === modalidade && item.estado === estado)
    );

    historicoRequisicoes.unshift(novaConsulta);



    if(historicoRequisicoes.length > 2 )
    {
        historicoRequisicoes.pop();
    }

    localStorage.setItem('historicoRequisicoes', JSON.stringify(historicoRequisicoes));

    renderizaHistorico();
}

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

    selectModalidade.innerHTML = `<option value="SEM FILTRO">Todas as modalidades</option>
                                   <option value="EAD">EAD</option>
                                   <option value="PRESENCIAL">Presencial</option>
    `;

    selectEstado = document.createElement('select');
    selectEstado.classList.add("select-estado");
    
    selectEstado.innerHTML = `<option value="SEM FILTRO">Todos os Estados</option>
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

 function criaDivResultado(div,tipo) 
{
     
    div.appendChild(selectModalidade);
    div.appendChild(selectEstado);

    if(tipo === "btn-matriculas-por-ano")
    {
        botaoBuscar = document.createElement("button");
        botaoBuscar.classList.add("btn-buscar-por-ano");
        botaoBuscar.innerText="Buscar";
        div.appendChild(botaoBuscar);
    }
    else
    {
        
        botaoBuscar = document.createElement("button");
        botaoBuscar.classList.add("btn-buscar-ranking");
        botaoBuscar.innerText="Buscar";
        div.appendChild(botaoBuscar);
    }

    divResultadoBusca = document.createElement('div');
    divResultadoBusca.classList.add('resultado');

    div.appendChild(divResultadoBusca);


}

async function buscaTotalPorAno(param1,param2)
{
  

    //PARAM1 = MODALIDADE E PARAM2 = ESTADO
    if(param1=== "SEM FILTRO" && param2 ==="SEM FILTRO")
    {
        
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
    else if( param1 !== 'SEM FILTRO' && param2 ==="SEM FILTRO")
    {
      
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
    else if( param1 === 'SEM FILTRO' && param2 !=="SEM FILTRO")
        {
      
            try{    
    
                const response = await fetch(`http://localhost:8080/matriculas/total-por-estado?estado=${param2}`);
                const matriculasPorAno= await response.json();
                const mapMatriculas = new Map(Object.entries(matriculasPorAno));
                
            return mapMatriculas;
    
            }catch(erro)
            {
                console.log("Erro ao puxar dados sem filtro",erro);
            }
        }
        else if( param1 !== 'SEM FILTRO' && param2 !=="SEM FILTRO")
            {
              
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


async function buscaRanking(param1,param2) {
     //PARAM1 = MODALIDADE E PARAM2 = ESTADO
     if(param1=== "SEM FILTRO" && param2 ==="SEM FILTRO")
        {
       
            try{
                const response = await fetch('http://localhost:8080/matriculas/ranking2022');
                const matriculasPorAno= await response.json();
                const mapMatriculas = new Map(Object.entries(matriculasPorAno));
    
            return mapMatriculas;
    
            }catch(erro)
            {
                console.log("Erro ao puxar dados sem filtro",erro);
            }
        }
        else if( param1 !== 'SEM FILTRO' && param2 ==="SEM FILTRO")
        {
          
            try{    
    
                const response = await fetch(`http://localhost:8080/matriculas/ranking2022?modalidade=${param1}`);
                const matriculasPorAno= await response.json();
                const mapMatriculas = new Map(Object.entries(matriculasPorAno));
                
            return mapMatriculas;
    
            }catch(erro)
            {
                console.log("Erro ao puxar dados sem filtro",erro);
            }
        }
        else if( param1 === 'SEM FILTRO' && param2 !=="SEM FILTRO")
            {
             
                try{    
        
                    const response = await fetch(`http://localhost:8080/matriculas/ranking2022PorEstado?estado=${param2}`);
                    const matriculasPorAno= await response.json();
                    const mapMatriculas = new Map(Object.entries(matriculasPorAno));
                    
                return mapMatriculas;
        
                }catch(erro)
                {
                    console.log("Erro ao puxar dados sem filtro",erro);
                }
            }
            else if( param1 !== 'SEM FILTRO' && param2 !=="SEM FILTRO")
                {
                    
                    try{    
            
                        const response = await fetch(`http://localhost:8080/matriculas/ranking2022PorEstado?estado=${param2}&modalidade=${param1}`);
                        const matriculasPorAno= await response.json();
                        const mapMatriculas = new Map(Object.entries(matriculasPorAno));
                        
                    return mapMatriculas;
            
                    }catch(erro)
                    {
                        console.log("Erro ao puxar dados sem filtro",erro);
                    }
                }
}

function criaTabelaPorMap(map,tipoDeBusca)
{

    const table = document.createElement('table');
    table.classList.add('tabela-matriculas');
    const thead = document.createElement("thead");
    thead.innerHTML =`<tr>
    <th>${tipoDeBusca}</th>
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

   divResultadoBusca.innerHTML ='';

    divResultadoBusca.appendChild(table);
}

criaSelects();

document.addEventListener('click', async function(event)
{

    let elementoClick = event.target;


    if(elementoClick.classList.contains('btn-matriculas-por-ano'))
    {
      
      criaDiv(divConteudo);
      criaDivResultado(divConteudoExibido,"btn-matriculas-por-ano");

    }
    else if(elementoClick.classList.contains("btn-buscar-por-ano"))
    {
      
       //divResultadoBusca.innerHTML ='';
       const map = await buscaTotalPorAno(selectModalidade.value,selectEstado.value);
      
      
       criaTabelaPorMap(map,"Ano");

        registraRequisicao("ano",selectModalidade.value,selectEstado.value);


    }
    else if(elementoClick.classList.contains("btn-ranking"))
    {
        criaDiv(divConteudo);
        criaDivResultado(divConteudoExibido,"btn-ranking");
    }
    else if(elementoClick.classList.contains("btn-buscar-ranking"))
    {
  
      //divResultadoBusca.innerHTML ='';

        const map = await buscaRanking(selectModalidade.value,selectEstado.value);

        criaTabelaPorMap(map,"Cursos");

        registraRequisicao("ranking",selectModalidade.value,selectEstado.value);
        
    }


}
);