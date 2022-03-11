import axios from "axios";
import MovieCard from "../../components/MovieCard";
import Pagination from "../../components/Pagination";
import { BASE_URL } from "utils/requests";
import { useEffect, useState } from "react";
import { MoviePage } from "types/movie";


function Listing() {

    //requisição correta com ReactHooks
    //Hooks são funções cujo comportamento está vinculado ao estado e ao ciclo de vida do React a partir de componentes funcionais.

    //fazendo requisição com useState
    const [pageNumber, setPageNumber] = useState(0);

    //estado = guardar no componente a pagina que foi carregada
    const [page, setPage] = useState<MoviePage>({
        content: [],
        last: true,
        totalPages: 0,
        totalElements: 0,
        size: 12,
        number: 0,
        first: true,
        numberOfElements: 0,
        empty: true
    });

    //quando mudar a página, ele faz a requisição novamente
    useEffect(() => {
        axios.get(`${BASE_URL}/movies?size=12page=${pageNumber}&sort=title`)
            .then(response => {
                const data = response.data as MoviePage;
                setPage(data);
            })
    }, [pageNumber])

    //teste de requisicao(errada)/roda mais de uma vez
    // axios.get(`${BASE_URL}/movies?size=12page=1`)
    //  .then(response =>{
    //     const data = response.data as MoviePage
    //   setPageNumber(data.number);
    //    console.log(response.data)
    //   })


//renderização dinamica
    return (
        <>
            <Pagination />
            <div className="container">
                <div className="row">
                    {page.content.map(movie => (
                        <div key={movie.id} className="col-sm-6 col-lg-4 col-xl-3 mb-3" >
                            <MovieCard movie={movie} />
                        </div>
                    )
                    )}
                </div>
            </div>
        </>
    );
}

export default Listing;