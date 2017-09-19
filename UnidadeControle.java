/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidadecontrole;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class UnidadeControle
{            
    String stage = "fetch";    
    public static boolean ok = false;
    public static String instructionsFileFolder = null;
    public static String dataFileFolderEXT = null;
    
    ArrayList<dataMem> dataList = new ArrayList(10);
    ArrayList<instructionsMem> instructionsList = new ArrayList(100);
           
    public String instructionsReader(String path) throws IOException
    {                  
        BufferedReader buffRead = new BufferedReader(new FileReader(path));        
        
        String linha = "";  
        String cmd[] = new String[10];
        
        while (true)
        {
            if (linha != null)
            {     
                cmd = linha.split("\n");
                for (int i=0; i < cmd.length; i++)
                {
                    String s = cmd[i];                    
                    instructionsMem instructionsObject = new instructionsMem(i,cmd[i]);            
                    instructionsList.add(instructionsObject);
                    String content = instructionsObject.getAddress(i);
                    System.out.println("IR: "+content);                                         
                }                                                          
            } else
              break;
            linha = buffRead.readLine();
        }            
            buffRead.close();
            
            return linha;
    }    
      
    public static int dataReader(String path, int address) throws IOException
    {                  
        BufferedReader buffRead = new BufferedReader(new FileReader(path));        
        
        int dataAddress = address;
        int tempAddress = 0, valueAddress = 0;
        String linha = "";  
        String cmd[] = new String[10];        
        
        while (true)
        {
            if (linha != null)
            {     
                cmd = linha.split(" ");                                
                try
                {                    
                    tempAddress = Integer.parseInt(cmd[0]);
                    valueAddress = Integer.parseInt(cmd[1]);                    
                }
                catch(NumberFormatException e)
                {
                    System.out.println(e);
                }
                                
                if(tempAddress == dataAddress)
                {                                        
                    break;                    
                }                                                                          
            } else            
            break;
                linha = buffRead.readLine();           
        }            
        buffRead.close();                          
        return valueAddress;
    }
    
    public static void dataWriter(String path, int address, int value) throws IOException
    {
        String tempPath = path;
        int tempAddress = address;
        int valueAddress = value;
        
        File arquivo = new File(tempPath);
        FileWriter writer = new FileWriter(arquivo, true);;                
        BufferedWriter buffer = new BufferedWriter(writer);
        
        // EXEMPLO PARA APAGAR UMA LINHA E SALVAR UM NOVO ARQUIVO COM O CONTEUDO ATUALIZADO
        /* 
                
        int lineToExclude = address;
        FileReader fileReader = null;  
        FileWriter fileWriter = null;   
        BufferedReader leitor = null;  
        String nomeDoArquivo = "C:/Users/ronal/Desktop/ArquivoNovo.txt";
        String arquivoConferir = dataFileFolderEXT;  
        String line = "";
            try
            {
                fileReader = new FileReader(new File(nomeDoArquivo));  
                fileWriter = new FileWriter(new File(arquivoConferir)); 
                leitor = new BufferedReader(fileReader);
                line = "";  
                    while ((line = leitor.readLine()) != null)
                    {                          
                        if(!line.trim().equals((""+lineToExclude).trim()))
                        { 
                                fileWriter.write(line + "\r\n"); 
                        }  
                    }                    
            }
            catch (IOException e)
            {  
                e.printStackTrace();  
            }
            try
                {  
                    fileWriter.close();
                    fileReader.close();  
                } 
                catch (IOException e) 
                {  
                    e.printStackTrace();  
                }                    

        */
        
        
        buffer.write(tempAddress + " "+ valueAddress + "\n");
        buffer.newLine();
        buffer.close();
        writer.close();
    }
        
    public static void main(String[] args)
    {       
        UnidadeControle _Unidade = new UnidadeControle();
        UIHome _interface = new UIHome();        
        _interface.setVisible(true);       
                        
        while(ok == false)
        {   
            System.out.println(ok);
            if(ok)
            {                
                System.out.println(instructionsFileFolder);
                System.out.println(dataFileFolderEXT);
                break;
            }            
        }
        
        String localFolder = instructionsFileFolder;
        String dataFileFolder = dataFileFolderEXT;        
                           
        String IR = "";
        String operacao = "", op1 = "", op2 = "", op3 = "";        
        
        int registers[] = new int[100];        
        int MBR = 0;
        int MAR = 0;
        int address = 0, address2 = 0, address3 = 0;
        int operator1 = 0, operator2 = 0, operator3 = 0;
        int PC = 1;
        int cycles = 0;
        
        boolean finish = true;
                
        try
        {                                    
            _Unidade.instructionsReader(localFolder);
            _Unidade.dataReader(dataFileFolder, 1);
            _interface.setTxtOutput(_interface.getTxtOutput()+"\nArquivos carregados para Memoria.");            
            JOptionPane.showMessageDialog(null, "Arquivos carregados para Memoria.");                        
        }
        catch(IOException e)
        {
            System.out.println("ERRO: "+e);
        }        
        
        System.out.println("Rodando Unidade de Controle...");
        System.out.println("Tamanho da lista de instrucoes: "+_Unidade.instructionsList.size());
        System.out.println("PC: "+PC);
                
        boolean init = true;
        while(init)
        {
            switch(_Unidade.stage)
            {
                case "fetch":                    
                    JOptionPane.showMessageDialog(null, "Fase de Carregamento/Fetch Iniciada");
                    _interface.setTxtOutput(_interface.getTxtOutput()+"\nFase de Carregamento/Fetch Iniciada");
                    if(PC < _Unidade.instructionsList.size())
                    {   
                        IR = _Unidade.instructionsList.get(PC).getAddress(PC);
                        PC++;
                        cycles++;
                        _Unidade.stage = "decode";
                        
                        //System.out.println("FETCH: "+ _Unidade.instructionsList.get(PC).getAddress(PC));
                    }                    
                    else
                    {
                        _interface.setTxtOutput(_interface.getTxtOutput()+"\nPrograma Finalizado. Nao ha mais instrucoes.");
                        JOptionPane.showMessageDialog(null,"Programa Finalizado. Nao ha mais instrucoes.");
                        _Unidade.stage = "final";
                    }
                break;
                case "decode":
                    _interface.setTxtOutput(_interface.getTxtOutput()+"\nFase de Decodificacao Iniciada");                    
                    JOptionPane.showMessageDialog(null, "Fase de Decodificacao Iniciada");
                    String cmd[] = new String[10];
                    cmd = IR.split(" ");                    
                    operacao = cmd[0];                                        
                    String temp = cmd[1];
                                        
                    cmd = temp.split(",");                    
                    
                    op1 = cmd[0];                    
                    char tempOP1[] = op1.toCharArray(); 
                    String auxAddress = "";
                    if(tempOP1[0] == 'R')
                    {
                        switch(tempOP1.length)
                        {
                            case 2:
                                auxAddress = (""+tempOP1[1]);
                                address = Integer.parseInt(auxAddress);
                                break;
                            case 3:
                                auxAddress = (""+tempOP1[1]+""+tempOP1[2]);
                                address = Integer.parseInt(auxAddress);
                                break;
                        }
                        
                        //address = Character.getNumericValue(tempOP1[1]);
                    }                    
                    else
                    {
                        operator1 = Integer.parseInt(op1);
                    } 
                    
                    op2 = cmd[1];
                    char tempOP2[] = op2.toCharArray();
                    String auxAddress2 = "";
                    switch (tempOP2[0])
                    {
                        case 'R':
                            
                            try
                            {                                        
                                address2 = Character.getNumericValue(tempOP2[1]);                                        
                                operator2 = dataReader(dataFileFolder, address2);
                            }
                            catch(IOException e)
                            {
                                System.out.println(e);
                            }
                            /*
                            switch(tempOP2.length)
                            {
                                case 2:
                                    try
                                    {                                        
                                        int auxData = Character.getNumericValue(tempOP2[1]);                                        
                                        operator2 = dataReader(dataFileFolder, auxData);
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    break;
                                case 3:
                                    try
                                    {
                                        auxAddress2 = (""+tempOP2[1]+""+tempOP2[2]);
                                        address2 = Integer.parseInt(auxAddress);
                                        operator2 = dataReader(dataFileFolder, address2);
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    break;
                            }
                            */                            
                            break;
                        case '<':
                            try
                            {
                                address2 = Character.getNumericValue(tempOP2[1]);                                        
                                operator2 = dataReader(dataFileFolder, address2);
                            }
                            catch(IOException e)
                            {
                                System.out.println(e);
                            }
                            break;
                        case 'x':
                            operator2 = 0;
                            break;
                        default:                            
                            operator2 = Integer.parseInt(op2);
                            break;
                    }
                                        
                    op3 = cmd[2];
                    char tempOP3[] = op3.toCharArray();
                    String auxAddress3 = "";
                    switch (tempOP3[0])
                    {
                        case 'R':
                            
                            try
                            {                                        
                                address3 = Character.getNumericValue(tempOP3[1]);                                        
                                operator3 = dataReader(dataFileFolder, address3);
                            }
                            catch(IOException e)
                            {
                                System.out.println(e);
                            }
                            break;
                        case '<':
                            try
                            {                                        
                                address3 = Character.getNumericValue(tempOP3[1]);                                        
                                operator3 = dataReader(dataFileFolder, address3);
                            }
                            catch(IOException e)
                            {
                                System.out.println(e);
                            }
                            break;
                        case 'x':
                            operator3 = 0;
                            break;
                        default:                            
                            operator3 = Integer.parseInt(op3);
                            break;
                    }
                    
                    // INSTRUCOES / OPERACOES
                    switch(operacao)
                    {
                        case "ADD":
                        
                            registers[tempOP1[1]] = operator2 + operator3;
                            operator1 = registers[tempOP1[1]];
                            operator2 = address;
                            _Unidade.stage = "execute";
                        cycles++; 
                        break;
                        case "SUB":
                                                           
                            registers[tempOP1[1]] = operator2 - operator3;
                            operator1 = registers[tempOP1[1]];
                            operator2 = address;
                            _Unidade.stage = "execute";
                        cycles++;      
                        break;
                        case "MUL":
                                                            
                            registers[tempOP1[1]] = (operator2 * operator3);
                            operator1 = registers[tempOP1[1]];
                            operator2 = address;
                            _Unidade.stage = "execute";
                        cycles++;         
                        break;
                        case "DIV":
                                     
                            try
                            {
                                registers[tempOP1[1]] = (operator2 / operator3);
                                operator1 = registers[tempOP1[1]];
                                operator2 = address;
                            }
                            catch(ArithmeticException e)
                            {
                                System.out.println(e);
                            }                            
                            _Unidade.stage = "execute";
                        cycles++;        
                        break;
                        
                        case "JMP":
                            
                            PC = operator1;                            
                            _Unidade.stage = "fetch";
                        cycles++;     
                        break;
                        
                        case "JMPZ":
                            
                            if(operator2 == 0)
                            {
                                PC = operator1;                            
                                _Unidade.stage = "fetch";
                            }
                            else
                                _Unidade.stage = "fetch";
                        cycles++;     
                        break;
                        
                        case "JMPM":
                            
                            if(operator2 > operator3)
                            {
                                PC = operator1;                            
                                _Unidade.stage = "fetch";
                            }
                            else
                                _Unidade.stage = "fetch";
                        cycles++;     
                        break;
                        
                        case "JMPL":
                            
                            if(operator2 < operator3)
                            {
                                PC = operator1;                            
                                _Unidade.stage = "fetch";
                            }
                            else
                                _Unidade.stage = "fetch";
                        cycles++;     
                        break;
                        
                        case "MOV":
                            
                            switch(tempOP2[0])
                            {
                                case 'R':                            
                                    try
                                    {
                                        int auxValue = dataReader(dataFileFolder, address2);
                                        operator2 = address;
                                        operator1 = auxValue;
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    _Unidade.stage = "execute";
                                    break;
                                case '<':
                                    try
                                    {
                                        int auxValue = dataReader(dataFileFolder, address2);
                                        operator2 = address;
                                        operator1 = auxValue;
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    _Unidade.stage = "execute";
                                    break;
                                case 'x':
                                    operator2 = address;
                                    operator1 = 0;
                                     _Unidade.stage = "execute";
                                    break;
                                default:     
                                    operator2 = address;
                                    operator1 = Integer.parseInt(op2);
                                     _Unidade.stage = "execute";
                                    break;
                            }                            
                        cycles++;     
                        break;
                        
                        case "STR":
                            
                            switch(tempOP2[0])
                            {
                                case 'R':                            
                                    try
                                    {
                                        int auxValue = dataReader(dataFileFolder, address2);
                                        operator2 = address;
                                        operator1 = auxValue;
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    _Unidade.stage = "execute";
                                    break;
                                case '<':
                                    try
                                    {
                                        int auxValue = dataReader(dataFileFolder, address2);
                                        operator2 = address;
                                        operator1 = auxValue;
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    _Unidade.stage = "execute";
                                    break;
                                case 'x':
                                    operator2 = address;
                                    operator1 = 0;
                                     _Unidade.stage = "execute";
                                    break;
                                default:     
                                    operator2 = address;
                                    operator1 = Integer.parseInt(op2);
                                     _Unidade.stage = "execute";
                                    break;
                            }                            
                        cycles++;     
                        break;
                        
                        case "LOAD":
                            
                            switch(tempOP2[0])
                            {
                                case 'R':                            
                                    try
                                    {
                                        int auxValue = dataReader(dataFileFolder, address2);
                                        operator2 = address;
                                        operator1 = auxValue;
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    _Unidade.stage = "execute";
                                    break;
                                case '<':
                                    try
                                    {
                                        int auxValue = dataReader(dataFileFolder, address2);
                                        operator2 = address;
                                        operator1 = auxValue;
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println(e);
                                    }
                                    _Unidade.stage = "execute";
                                    break;
                                case 'x':
                                    operator2 = 0;
                                     _Unidade.stage = "execute";
                                    break;
                                default:     
                                    operator2 = address;
                                    operator1 = Integer.parseInt(op2);
                                     _Unidade.stage = "execute";
                                    break;
                            }
                        cycles++;     
                        break;
                    }                             
                break;
                case "execute":
                    _interface.setTxtOutput(_interface.getTxtOutput()+"\nFase de Execucao Iniciada");
                    JOptionPane.showMessageDialog(null, "Fase de Execucao Iniciada");
                    _interface.setTxtOutput(_interface.getTxtOutput()+"\nOPERACAO: "+operacao +"  ["+op1 +"]  ["+op2 +"]  ["+op3 +"]");
                    JOptionPane.showMessageDialog(null, "OPERACAO: "+operacao +"  ["+op1 +"]  ["+op2 +"]  ["+op3 +"]");
                    int conf = JOptionPane.showConfirmDialog(null, "Deseja encerrar a execucao?", "Encerrar Execucao do Simulador?",0);
                    if(conf == 0)
                    {
                        init = false;
                    }
                    
                    try
                    {
                        MAR = operator2;
                        MBR = operator1;
                        dataWriter(dataFileFolder, MAR, MBR);
                    }
                    catch(IOException e)
                    {
                        System.out.println(e);
                    }
                    
                    cycles++;
                    _Unidade.stage = "fetch";
                break;
                case "final":                    
                    if(finish)
                    {
                        JOptionPane.showMessageDialog(null, "Qtde de Ciclos: "+cycles);
                        finish = false;
                    }                                                           
                break;
            }                    
        }                
    }       
}
