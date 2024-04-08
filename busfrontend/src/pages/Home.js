import { useEffect, useState } from "react";
import busService from "../services/busServices";
import { Link, useNavigate } from "react-router-dom";

function Home() {
  const [cities,setCities] = useState([])
  const [originCity,setOriginCity] = useState("")
  const [destinationCity,setdestinationCity] = useState("")
  const navigate = useNavigate();

  useEffect(()=>{
    busService.getCitys().then((response)=>{
      console.log(response.data)
      setCities(response.data)
    })
  },[])

  console.log(originCity)

  const search = (id)=>{
      navigate(`/reserva/${id}`)
  }

  return (
    <div className="flex flex-col items-center gap-10 align-middle h-screen place-content-center">
      <h1 className="w-[20%]">Origin:</h1>
      <select id="origin" class="select select-bordered w-[20%]" onChange={e => setOriginCity(e.target.value)}>
        <option disabled selected>Origin ...</option>
        {cities.map((city)=>
              <option value={city.id}>{city.name}</option>
            )}
      </select>
      <h1 className="w-[20%]">Destination:</h1>
      <select id="destination" class="select select-bordered w-[20%]" onChange={e => setdestinationCity(e.target.value)}>
        <option disabled selected>Destination ...</option>
        {cities.map((city)=>
              <option value={city.id}>{city.name}</option>
            )}
      </select>
      <Link id="reservate" className="w-[20%]" to={`trips?origin=${originCity}&destination=${destinationCity}`}><button className="btn btn-primary w-full">Search</button></Link>
      <h1>Already Have reservation Search here:</h1>
      <label class="input input-bordered flex items-center gap-2 w-[20%]">
        <input type="number" class="grow" placeholder="Search" onKeyDown={(e) => (
            e.key === "Enter" ? search(e.target.value) : null
        )}/>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="w-4 h-4 opacity-70"><path fill-rule="evenodd" d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z" clip-rule="evenodd" /></svg>
      </label>
    </div>
  );
}

export default Home;
