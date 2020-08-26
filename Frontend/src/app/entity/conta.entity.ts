import {UsuarioEntity} from './usuario.entity';

export class ContaEntity {
  id: number;
  numeroConta: string;
  saldo: number;
  usuario: UsuarioEntity;


}
