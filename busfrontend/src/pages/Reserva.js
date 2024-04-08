import { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import busService from "../services/busServices";


function Reserva(){
    let { id } = useParams();
    let { state } = useLocation();
    const[reserv,setReserv] = useState("");

    useEffect(()=>{
        busService.getReservation(id).then((response)=>{
            console.log(response.data)
            setReserv(response.data)
        })
    },[])
    if (reserv !== ""){
        return(
            <div className="flex flex-col items-center gap-10 align-middle h-screen place-content-center">
                {state !== null && state.new && <div role="alert" class="alert alert-success w-[20%] mt-[-100px]">
                    <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current shrink-0 h-6 w-6" fill="none" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" /></svg>
                    <span>Your Reservation has been confirmed!</span>
                </div>}
                <h1>Reserva Nº {id}</h1>
                <div class="overflow-x-auto">
                    <table class="table table-zebr">
                        <tbody>
                        <tr>
                            <th>Data</th>
                            <td>{reserv.trip.date}</td>
                        </tr>
                        <tr>
                            <th>Bus</th>
                            <td>{reserv.trip.bus.name}</td>
                        </tr>
                        <tr>
                            <th>Origin</th>
                            <td>{reserv.trip.originCity.name}</td>
                        </tr>
                        <tr>
                            <th>Destination</th>
                            <td>{reserv.trip.destinationCity.name}</td>
                        </tr>
                        <tr>
                            <th>Cost</th>
                            <td>{reserv.trip.cost}</td>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <td>{reserv.name}</td>
                        </tr>
                        <tr>
                            <th>Morada</th>
                            <td>{reserv.morada}</td>
                        </tr>
                        <tr>
                            <th>CardType</th>
                            <td>{reserv.cardType}</td>
                        </tr>
                        <tr>
                            <th>cardNumber</th>
                            <td>{reserv.cardNumber}</td>
                        </tr>
                        <tr>
                            <th>Nif</th>
                            <td>{reserv.nif}</td>
                        </tr>
                        <tr>
                            <th>ocupancy</th>
                            <td>{reserv.trip.ocupancy}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }else{
        return(<h1 className="text-6xl">Loading</h1>)
    }
}

export default Reserva;