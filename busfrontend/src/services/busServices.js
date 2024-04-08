import client from "./client";

const busService = {
    async getCitys(){
        return (await client.get("/city"))
    },

    async getTrips(origin,destination,price){
        return (await client.get(`/trips?originCityId=${origin}&destinationCityId=${destination}&currency=${price}`))
    },

    async reservate(id,name,morada,nif,cardType,cardNumber){
        return (await client.post("/reservation",{
            tripId:id,
            name:name,
            morada:morada,
            nif:nif,
            cardType:cardType,
            cardNumber:cardNumber,
        }))
    },

    async getReservation(id){
        return (await client.get(`/reservation/${id}`))
    }


}

export default busService;