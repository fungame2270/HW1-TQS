import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import busService from "../services/busServices";


function useQuery() {
    const { search } = useLocation();
  
    return React.useMemo(() => new URLSearchParams(search), [search]);
}


function Trips(){
    let query = useQuery();
    const [trips,setTrips] = useState([]);
    const [origin,setOrigin] = useState("");
    const [destination,setDestination] = useState("");
    const [price,setPrice] = useState("USD");

    useEffect(()=>{
        busService.getTrips(query.get("origin"),query.get("destination"),price).then((response)=>{
            console.log(response.data)
            setTrips(response.data)
            setOrigin(response.data[0].originCity.name)
            setDestination(response.data[0].destinationCity.name)
        })
    },[price])

    return(
        <div className="px-[20%] pt-10">
            <h1 id="Title" className=" text-4xl">Viagens de {destination} até {origin}</h1>
            <select class="select select-bordered w-[20%]" onChange={e => setPrice(e.target.value)}>
                <option value={"USD"}>USD</option>
                <option value={"EUR"}>EUR</option>
                <option value={"CAD"}>CAD</option>
                <option value={"JPY"}>JPY</option>
            </select>
            <div class="overflow-x-auto pt-2">
                <table class="table table-zebra">
                    <thead>
                    <tr>
                        <th>data</th>
                        <th>bus</th>
                        <th>cost</th>
                        <th>AvailableSpace</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {trips.map((trip,index)=>
                        <tr>
                            <th>{trip.date}</th>
                            <td>{trip.bus.name}</td>
                            <td>{(Math.round(trip.cost * 100) / 100).toFixed(2)}</td>
                            <td>{trip.bus.capacity - trip.ocupancy}</td>
                            <td><Link id={`reserva-${index}`} to={`/reservate/${trip.id}`}><button className="btn btn-primary h-[10%]">Reservate</button></Link></td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Trips;