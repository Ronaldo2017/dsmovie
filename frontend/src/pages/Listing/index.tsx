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

    useEffect(() => {
        axios.get(`${BASE_URL}/movies?size=12page=1`)
        .then(response =>{
            const data = response.data as MoviePage
            setPageNumber(data.number);
            console.log(response.data)
          })
    }, [])
    
    //teste de requisicao(errada)/roda mais de uma vez
   // axios.get(`${BASE_URL}/movies?size=12page=1`)
    //  .then(response =>{
     //     const data = response.data as MoviePage
       //   setPageNumber(data.number);
      //    console.log(response.data)
     //   })



    return (
        <>
        <p>{pageNumber}</p>
            <Pagination />
            <div className="container">
                <div className="row">
                    <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>
                </div>
            </div>
        </>
    );
}

export default Listing;