import { Pipe, PipeTransform } from '@angular/core';


/**
 * Pipe para transformar um numero em valor formatado em reais
 * {{ valor | real }}
 */
@Pipe({ name: 'real', pure: true })
export class RealPipe implements PipeTransform {
    transform(value: number): string {
        if (value === null) {
            return null;
        }
        const formattedValue = value.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' });
        return formattedValue;
    }
}