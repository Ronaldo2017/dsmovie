
import FormCard from "components/FormCard";
import { Link, useParams } from "react-router-dom";
import { Movie } from 'types/movie';


function Form() {

    //pega o parametro da requisicao e passa para o formcard
    const params = useParams();

    return (
        <FormCard movieId={`${params.movieId}`} />
    );
}

export default Form;