**# Simulador de Algoritmos de Paginação

## 📌 Introdução

Projeto da disciplina de **Projeto de Sistemas Operacionais**, focado em **algoritmos de substituição de página**.  
O simulador implementa os algoritmos **Aging**, **FIFO** (com bit de referência), **LRU** e **NFU**.

1. [ ] Foi desenvolvido em **Java (versão 21)** e utiliza a biblioteca **JavaFX (Versão 21)** para construção da interface gráfica.

## 👥 Autores

- [Pedro Guilherme](https://github.com/pgsouto)  
**Matrícula**: 2223875

- [Larissa Elias](https://github.com/LariElias)  
**Matrícula**: _a definir_

---

## ⚙️ Instalação do Código

Siga os passos abaixo para clonar ou baixar o projeto:

### 🔁 Opção 1: Clonar o repositório

```bash
    git clone https://github.com/pgsouto/PageReplacementSimulator.git
    cd PageReplacementSimulator
```
   
####  OU
### 📦 Opção 2: Baixar o .zip (recomendado/testado)

1. [Acesse o repositório no GitHub](https://github.com/pgsouto/PageReplacementSimulator)
2. Baixe o código como `.zip` e extraia os arquivos
3. Abra o projeto no **IntelliJ** (recomendado/testado)

---

## ☕ Configuração do JavaFX

### 1. Baixe a biblioteca JavaFX

- Acesse o site oficial: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- Na seção de downloads:
    - Selecione a versão **LTS** (recomendada: **21.0.6 / 21.0.7** ou superior)
    - Escolha o seu sistema operacional
    - Escolha a arquitetura correta (geralmente **x64**)
    - Selecione o tipo **SDK**

⚠️ **Importante:** Certifique-se de baixar a versão LTS no formato SDK.

### 2. Configure o JavaFX no IntelliJ

1. No menu superior, clique em **File > Project Structure**
2. Em **Project Settings**, vá até **Libraries**
3. Clique no botão `+` (New Project Library) e selecione **Java**
4. Navegue até a pasta `lib` do SDK do JavaFX que você baixou
5. Adicione todos os arquivos `.jar` dessa pasta
6. Se for exibido o modal **Selected Roots**, selecione a raiz do seu projeto
7. Clique em **Apply** e depois em **OK**

### 3. Configurar a classe `Simulator`
1. No menu superior, clique em **Run > Edit Configurations**
2. No canto superior esquerdo, clique no botão `+` (Add New Configuration) e selecione **Application**
3. No campo **Name** insira o nome **Simulator** (poderá ser outro nome)
4. Do lado direito da seção **Build and Run** clique na opção **Modify Options > Add VM Options**
5. No campo **Class** digite a classe `Simulator`
6. No campo **VM Options** insira o seguinte comando:

- Exemplo de comando para Linux
```
--module-path /home/user/caminho/openjfx-21.0.6_linux-x64_bin-sdk/javafx-sdk-21.0.6/lib --add-modules javafx.controls,javafx.fxml 
```
- Exemplo de comando para Windows
```
--module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml
```
⚠️ **Importante:** Não esqueça de modificar o caminho para o seu JavaFX.
7. Clique em **Apply > Ok****
