/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidadecontrole;

/**
 *
 * @author ronal
 */
public class dataMem
{      
    int i;
    String str;
    public dataMem(int address, String content)
    {
        i = address;
        str = content;     
    }
    
    public String getAddress(int address)
    {
        return str;
    }
}
