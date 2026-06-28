# Simulador de Força Eletrostática

  Três esferas condutoras iguais estão posicionadas de maneira que formam um triângulo equilátero. É feito um experimento onde elas são conectadas e desconectadas por fios condutores e a segunda esfera é ligada à terra.<br>
  O programa consiste em simular esse experimento e, através da Lei de Coulomb, calcular qual será a carga final de cada esfera e o módulo de força eletrostático entre cada par de esferas.<br>
  Ele funciona da seguinte maneira: o usuário irá inserir o valor da carga de cada esfera(nC) e a distância(cm) entre cada uma. Após isso serão mostrados os valores do módulo de força entre cada par de esferas e em seguida será feita uma simulação onde o usuário poderá escolher a opção entre ligar o fio entre as esferas e aterrar a segunda esfera.<br>
  Por fim, quando o usuário decidir encerrar a simulação, serão mostrados os valores da carga final de cada esfera e o módulo eletrostático final entre cada par.<br>

**Fórmulas:**<br>
Constante eletrostática = 9x10^9;<br>
Carga inicial de cada esfera(Conversão para nC) = valor inserido * 1x10^-9;<br>
Distância(Conversão para cm) = valor inserido/100;<br>
Módulo de Força Elétrostático = Constante eletrostática * ((|primeira esfera * segunda esfera|) / (distância)²);<br>
Média entre as cargas = (carga da primeira esfera * carga da segunda esfera) / 2;<br>

**Imagens da Interface:**<br>

![Interface1](Interface1.png)
![Interface2](Interface2.png)
![Interface1](Interface3.png)
![Interface1](Interface4.png)
![Interface1](Interface5.png)
