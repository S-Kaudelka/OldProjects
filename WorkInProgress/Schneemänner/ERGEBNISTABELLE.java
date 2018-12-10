/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;

public class ERGEBNISTABELLE {
    private ResultSet result;
    
    ERGEBNISTABELLE(ResultSet queryResult_p)
    {
        result = queryResult_p;
    }
    
    public boolean NaechstenDatensatzPositionieren(){
        try{
            return result.next();
        } catch(SQLException e){
            return false;
        }
    }    
    
    public int LeseGanzzahl(int Spaltennummer)
    {
        try{
            return result.getInt(Spaltennummer);
        }
        catch(SQLException e){
            return Integer.MIN_VALUE;
        }
    }
    
    public int LeseGanzzahl(String Spaltenname)
    {
        try{
            return result.getInt(Spaltenname);
        }
        catch(SQLException e){
            return Integer.MIN_VALUE;
        }
    }
    
    public String LeseText(int Spaltennummer)
    {
        try{
            return result.getString(Spaltennummer);
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public String LeseText(String Spaltenname)
    {
        try{
            return result.getString(Spaltenname);
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public double LeseFliesskommazahl(int Spaltennummer)
    {
        try{
            return result.getDouble(Spaltennummer);
        }
        catch(SQLException e){
            return Double.NaN;
        }
    }
    
    public double LeseFliesskommazahl(String Spaltenname)
    {
        try{
            return result.getDouble(Spaltenname);
        }
        catch(SQLException e){
            return Double.NaN;
        }
    }
    
    public boolean LeseWahrheitswert(int Spaltennummer)
    {
        try{
            return result.getBoolean(Spaltennummer);
        }
        catch(SQLException e){
            return false;
        }
    }
    
    public boolean LeseWahrheitswert(String Spaltenname)
    {
        try{
            return result.getBoolean(Spaltenname);
        }
        catch(SQLException e){
            return false;
        }
    }
    
    public Date LeseDatum(int Spaltennummer)
    {
        try{
            return result.getDate(Spaltennummer);
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public Date LeseDatum(String Spaltenname)
    {
        try{
            return result.getDate(Spaltenname);
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public Time LeseZeit(int Spaltennummer)
    {
        try{
            return result.getTime(Spaltennummer);
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public Time LeseZeit(String Spaltenname)
    {
        try{
            return result.getTime(Spaltenname);
        }
        catch(SQLException e){
            return null;
        }
    }
    
    
}
