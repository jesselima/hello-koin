package com.jeziellago.hellokoin.features.hello.di

import com.jeziellago.hellokoin.core.api.ApiService
import com.jeziellago.hellokoin.features.hello.data.api.HelloService
import com.jeziellago.hellokoin.features.hello.data.repository.HelloRepositoryImpl
import com.jeziellago.hellokoin.features.hello.domain.repositories.HelloRepository
import com.jeziellago.hellokoin.features.hello.domain.usecase.HelloUseCase
import com.jeziellago.hellokoin.features.hello.presentation.HelloViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

// TODO 4 - Como organização do projeto, é uma boa prática criar um package de "di" para cada
//  feature ou conjunto de features (ex: quando uma funcionalidade tem vários passos). Dentro de "di"
//  Crie uma arquivo onde as instancias das classes são configuradas em moculos (daí a função
//  module {...}).
//  Dentro de cada módulo instancias de sus classes podem ser congiguradas através das funções:
//  - factory { ... } Para criar instâncias
//  - single { ... } Para criar instancias únicas que vão estar disponíveis durante o ciclo de vida
//      do App.
//  - viewModel { ... }  Para criar as intencias de ViewModels (Ufa! usar ViewModelFactory no more!)
internal val helloModule = module {

    //  TODO 6 - Adicionar suas classe aqui apenas quando as classes que ele depende já
    //   estiverem prontas. uma boa prática para evitar errors é começar pelas camadas da "ponta"
    //   da sua aplicação: DATA > DOMAIN > PRESENTATION. eNTÃO

    // TODO INFO - Exemplo de configurar uma classe de service (com seus métodos e endpoints do Retrofit)
     factory {
        ApiService.createService(HelloService::class.java)
    }

    // TODO INFO - Exemplo de configuração de reposítory - classe de interface (HelloRepository),
    //  junto com a classe de implementação (HelloRepositoryImpl)
    //  Veja que o HelloRepositoryImpl recebe um service no construtor. Se seu service não estiver
    //  configurado no Koin (como está na linha 29), vai dar erro de "Definition not found" quando
    //  a instancia do seu repository tentar ser criada.
    factory<HelloRepository> {
        // TODO INFO -> a função get() vai buscar a classe pelo seu tipo no grafo do Koin. por isso se
        //  se esta classe que está no contrutor não estiver no configurada no Koin, vai dar erro.
        HelloRepositoryImpl(helloService = get() )
    }

    // TODO INFO - Exemplo de configuração de UseCase que recebe um repository no construtor.
    //  Mais uma vez, lembre-se que se seu repository não estiver configurado no Koin vai dar errro.
    factory {
        HelloUseCase(helloRepository = get())
    }

    // TODO INFO - Exemplo de configuração do ViewModel que recebe um UseCase no seu construtor.
    viewModel {
        HelloViewModel(
            helloUseCase = get()
        )
    }
}

// TODO - 4 a função module { ... } retorna um objeto de Module do koin que precisa ser carretado no
//  grafo do Koin. A funçao loadKoinModules() recebe o seu módulo com as configurações que você fez
//   (ver linha 22 a 41).
internal val helloDependencies by lazy {
    loadKoinModules(helloModule)
}

// TODO 5 - Esta função que aciona o carregamento do módulo deve ser chamada antes de voce usar
//  qualquer classe que foi configurada no seu módulo "helloModule". Ela pode ser adicionada na
//  Sua classe de application.
fun initHelloDependencies() = helloDependencies