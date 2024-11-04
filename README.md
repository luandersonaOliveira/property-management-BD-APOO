# property-management-BD-APOO

 Banco de Dados e Analise de Projetos Orientado a Objetos

## Minimundo

### Cenário - Sistema de Gerenciamento de Aluguel de Imóveis

## Descrição

  Uma imobiliária tem a proposta de gerenciar o aluguel de imóveis. O sistema dessa imobiliária deve permitir o cadastro de inquilinos, imóveis, proprietários, contratos de locação e os dados dos pagamentos. O usuário pode ser cadastrado tanto como proprietário como inquilino. O inquilino pode alugar um ou mais imóveis por um determinado período (Na verdade, o inquilino só pode alugar somente um Imovel. |Falta Implementar a nova regra!|), enquanto isso, o sistema deve calcular o valor total do aluguel, registrar os pagamentos mensais dos inquilinos, as devoluções, multas por atraso no pagamento (Caso ocorra, |Falta Implementar a nova regra!|) e informar o estado de cada Imovel. Cada Imovel tem um único proprietário e o proprietário é sempre associado a um Imovel especificamente, o que garante o registro de posse entre o Imovel e o proprietário (No caso,Imovel SIM depende de Proprietário. O Imovel é sempre associado a um Proprietário).

## Realidade do Problema

- A pessoa pode se referir a proprietário ou inquilino, e pode ter mais de um número de telefone e residir em um endereço composto por Rua, número, bairro, cidade, estado e CEP (Na verdade, só pode ter somente 1 numero de telefone |Falta Implementar a nova regra!|).

- O Imovel sempre irá pertencer a um único proprietário, porém, ao longo do tempo, ele pode ser alugado por vários inquilinos diferentes (|Falta Implementar a nova regra!|).

- O contrato de locação é um documento estabelecido entre o inquilino e o Imovel por um tempo específico, com os valores e as condições do contrato pré definidas, incluindo possíveis multas por atraso no pagamento (|Falta Implementar a nova regra!|).

- O pagamento deve ser registrado a cada contrato que for firmado, contendo as informações como data de pagamento, valor a pagar, método de pagamento e se o pagamento está pendente ou já foi realizado (|Falta Implementar a nova regra!|).

## Os requisitos devem ser atendidos pela modelagem de dados

- Pessoa
    A entidade pessoa está interligada com as entidades inquilino e proprietário, pois, o usuário pode desempenhar um desses papéis. É cadastrada com nome, CPF, cargo, endereço e telefone (Imagino que essa classe não seja necessária!).

- Inquilino
    Cada inquilino possui um código para identificação (ID), nome, CPF, telefone, email, saldo e Imovel. O inquilino pode alugar vários imóveis (O inquilino pode alugar somente um Imovel |Falta Implementar a nova regra!|).

- Proprietário
    Cada Proprietário possui um código para identificação (ID). É cadastrado com nome, CPF, telefone email e uma lista de Imoveis. O proprietário pode ter vários imóveis cadastrados, mas pode apenas disponibilizar um por vez para locação.

- Imovel
    Cada Imovel possui um código para identificação (ID), endereço, tipo de Imovel (Apartamento, Casa, Imovel Comercial. (|Falta Implementar a nova regra!|)), número de quartos (Numero de quartos, só é atribuído a herença de residencial), valor do aluguel e status do Imovel (se está disponível para locação, alugado (Basicamente se esta desocupado ou ocupado)). Um Imovel pode ser alugado por vários inquilinos, durante momentos diferentes.

- Contrato de Locação
    O contrato é composto pela data de início, data de término, data de devolução do Imovel (|Falta Implementar a nova regra!|), valor total do aluguel, multa (Caso ocorra |Falta Implementar a nova regra!|), Proprietário, Imovel e Inquilino. O contrato é referido a um Imovel e um inquilino em específico.
  
- Multa
    Ela é calculada com base nos dias de atraso no pagamento do aluguel. Ela está relacionada com o contrato de locação (|Falta Implementar a nova regra!|).

- Pagamento
    É composto por data, valor do pagamento, método de pagamento (Cartão de crédito, boleto, espécie *(Não é necessario)) e o status do pagamento (Pago ou pendente |Falta Implementar a nova regra!|). O pagamento se refere ao contrato de locação e é registrado mensalmente.

## Entidades

Pessoa (Imagino que essa classe não seja necessária!)

  -CPF (PK)
  -Nome
  -Cargo (Inquilino ou Proprietário)
  -Endereço (Atributo composto: Rua, Número, Bairro, Cidade, Estado, CEP)
  -Telefone (Atributo multivalorado)

Proprietário

    - IdProprietário (PK)
    - Nome
    - CPF 
    - Telefone (Atributo multivalorado)
    - Email
    - Imovel (Atributo multivalorado)

Inquilino

    - IdInquilino (PK)
    - Nome 
    - CPF
    - Telefone (Atributo multivalorado)
    - Email
    - Saldo
    - Imovel  (Atributo multivalorado)

Imovel

    - IdImovel (PK)
    - Endereço (Atributo composto: Rua, Número, Bairro, Cidade, Estado, CEP)
    - Valor do Aluguel
    - Tipo (Casa, apartamento ou comércio)
    - Ocupação (Disponível, alugado)
    - Proprietário (Atributo multivalorado)
    - Inquilinos

Contrato de Locação

    - IdContrato (PK)
    - Data de Início
    - Data de Término
    - Data de Devolução do Imovel
    - Valor total do aluguel
    - Multas (caso ocorra)
    - Proprietário
    - Inquilino
    - Imovel

Pagamento

    - IdPagamento (PK)
    - Data de pagamento
    - Valor a Pagar
    - Método de Pagamento (Cartão de crédito, boleto, espécie (Não é necessario))
    - Status (Pago ou pendente)
    - IdContrato (FK herdada de Contrato de Locação)

## Relacionamentos

1:1 - Proprietário-Imovel:
    O Imovel pode ter apenas um proprietário e o proprietário deve ter apenas um Imovel cadastrado em seu nome no sistema. Para cadastrar outro Imovel, ele deve desvincular o antigo (O Proprietário pode ter nenhum ou vários Imóveis, Imovel pode ter somente um Proprietário).

1:N - Contrato-Inquilino:
    O inquilino pode ter vários contratos firmados com o tempo, mas, cada contrato se refere a apenas um inquilino (Inquilino pode ter nenhum ou somente um Contrato, Contrato tem somente um Inquilino).

N:N - Inquilino-Imovel:
    O Imovel pode ser alugado por vários inquilinos ao longo do tempo e um inquilino pode ocupar vários imóveis, mas não simultaneamente (Somente um Inquilino pode alugar um Imovel).
