import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import busService from "../services/busServices";


function Reservate(){
    let {id} = useParams();
    const [name,setName] = useState("");
    const [morada,setMorada] = useState("");
    const [nif,setNif] = useState("");
    const [card,setCard] = useState("");
    const [cardNumber,setCardNumber] = useState("");
    const navigate = useNavigate();

    console.log(card)

    const reservate = ()=>{
        busService.reservate(id,name,morada,nif,card,cardNumber).then((response)=>{
            if (response.status === 201){
                navigate(`/reserva/${response.data.id}`,{state:{new:true}})
            }
        })
    }

    return(
        <div className="flex flex-col gap-3 place-content-center items-center h-screen">
            <h1>Reservation</h1>
            <h2 className="w-[20%]">Name:</h2>
            <input id="name" type="text" placeholder="name" class="input input-bordered  w-[20%]" onChange={e => setName(e.target.value)}/>
            <h2 className="w-[20%]">Morada:</h2>
            <input id="morada" type="text" placeholder="morada" class="input input-bordered w-[20%] " onChange={e => setMorada(e.target.value)}/>
            <h2 className="w-[20%]">Nif:</h2>
            <input type="text" placeholder="nif" class="input input-bordered w-[20%] " onChange={e => setNif(e.target.value)}/>
            <h2 className="w-[20%]">Card Type:</h2>
            <select class="select select-bordered w-[20%] " onChange={e => setCard(e.target.value)}>
                <option value={""}disabled selected>CardType</option>
                <option value={"credit"}>Credit</option>
                <option value={"debit"}>Debit</option>
            </select>
            <h2 className="w-[20%]">Card Number:</h2>
            <input type="text" placeholder="Card Number" class="input input-bordered w-[20%] " onChange={e => setCardNumber(e.target.value)}/>
            <button id="reservate" className="btn btn-primary w-[20%] mt-5" onClick={()=> reservate()}>Reservate</button>
        </div>
    )
}

export default Reservate;