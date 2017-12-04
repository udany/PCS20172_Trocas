package model.enums;

public enum State {
    ACRE("Acre", "AC", Region.North),

    ALAGOAS("Alagoas", "AL", Region.NorthEast),

    AMAPA("Amapá", "AP", Region.North),
    
    AMAZONAS("Amazonas", "AM", Region.North),
    
    BAHIA("Bahia", "BA", Region.NorthEast),
    
    CEARA("Ceará", "CE", Region.NorthEast),
    
    DISTRITOFEDERAL("Distrito Federal", "DF", Region.CenterWest),
    
    ESPIRITOSANTO("Espírito Santo", "ES", Region.SouthEast),
    
    GOIAS("Goiás", "GO", Region.CenterWest),
    
    MARANHAO("Maranhão", "MA", Region.NorthEast),
    
    MATOGROSSO("Mato Grosso", "MT", Region.CenterWest),
    
    MATOGROSSODOSouth("Mato Grosso do South", "MS", Region.CenterWest),
    
    MINASGERAIS("Minas Gerais", "MG", Region.SouthEast),
    
    PARA("Pará", "PA", Region.North),
    
    PARAIBA("Paraíba", "PB", Region.NorthEast),
    
    PARANA("Paraná", "PR", Region.South),
    
    PERNAMBUCO("Pernambuco", "PE", Region.NorthEast),
    
    PIAUI("Piauí", "PI", Region.NorthEast),
    
    RIODEJANEIRO("Rio de Janeiro", "RJ", Region.SouthEast),
    
    RIOGRANDEDONorth("Rio Grande do North", "RN", Region.NorthEast),
    
    RIOGRANDEDOSouth("Rio Grande do South", "RS", Region.South),
    
    RONDONIA("Rondônia", "RO", Region.North),
    
    RORAIMA("Roraima", "RR", Region.North),
    
    SANTACATARINA("Santa Catarina", "SC", Region.South),
    
    SAOPAULO("São Paulo", "SP", Region.SouthEast),
    
    SERGIPE("Sergipe", "SE", Region.NorthEast),
    
    TOCANTINS("Tocantins", "TO", Region.North);


    private String name;
    private String acronym;
    private Region region;
    State(String nome, String acronym, Region region) {
        this.name = nome;
        this.acronym = acronym;
        this.region = region;
    }

    /**
     * Retorna nome
     *
     * @return nome
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna sigla
     *
     * @return sigla
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Retorna região
     * @return regiao
     */
    public Region getRegion() {
        return region;
    }
}