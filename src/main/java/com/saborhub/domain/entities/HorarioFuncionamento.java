package com.saborhub.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class HorarioFuncionamento {
    private Set<DayOfWeek> diasAberto;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;

    public HorarioFuncionamento(Set<DayOfWeek> diasAberto, LocalTime horaAbertura, LocalTime horaFechamento) {
        this.diasAberto = diasAberto;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }
}
