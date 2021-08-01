package com.jeziellago.hellokoin.features.hello.data.repository

import com.jeziellago.hellokoin.features.hello.data.api.HelloService
import com.jeziellago.hellokoin.features.hello.data.models.HelloResponse
import com.jeziellago.hellokoin.features.hello.domain.repositories.HelloRepository

// TODO INFO - Exemplo de uso de instancias criadas pelo Koin.
//  Quando uma instancia do HelloRepository e HelloRepositoryImpl for criada, o Koin vai se
//  encarregar de criar a instancia no HelloService e injetar este instancia no construtor do
//  HelloRepositoryImpl. Assim você não criará instancias no mesmo lugar que ela está sendo usada.
//  Existem príncipios do SOLID aplicados aqui que poderão ser detalhados mais a frente.
internal class HelloRepositoryImpl(
    val helloService: HelloService
) : HelloRepository {

    override suspend fun fetchHelloMessage(): HelloResponse? {
        return helloService.getHelloMessage()
    }

}