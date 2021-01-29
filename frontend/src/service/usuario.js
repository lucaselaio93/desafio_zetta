import { http } from './config'
export default {
    list:()=>{
        return http.get('/usuarios')
    },
    save:(usuario) => {
        return http.post('/usuarios', usuario)
    },
    delete:(id) => {
        return http.delete('/usuarios/'+id, {data: id})
    },
    update:(usuario) => {
        return http.put('/usuarios/'+usuario.id, usuario)
    }
}