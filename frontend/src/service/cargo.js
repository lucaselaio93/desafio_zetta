import { http } from './config'
export default {
    list:()=>{
        return http.get('/cargos')
    },
    save:(cargo) => {
        return http.post('/cargos', cargo)
    },
    delete:(id) => {
        return http.delete('/cargos/'+id, {data: id})
    },
    search:(id)=>{
        return http.get('/cargos/'+id, {data: id})
    },
}