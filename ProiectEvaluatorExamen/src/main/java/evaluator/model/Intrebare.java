package evaluator.model;

import evaluator.exception.InputValidationFailedException;

public class Intrebare {

    private String enunt;
    private String varianta1;
    private String varianta2;
    private String varianta3;
    private Integer variantaCorecta;
    private String domeniu;

    public Intrebare() {
    }

    public Intrebare(String enunt, String varianta1, String varianta2, String varianta3,
                     Integer variantaCorecta, String domeniu) throws InputValidationFailedException {
        this.enunt = enunt;
        this.varianta1 = varianta1;
        this.varianta2 = varianta2;
        this.varianta3 = varianta3;
        this.variantaCorecta = variantaCorecta;
        this.domeniu = domeniu;
    }


    public String getEnunt() {
        return enunt;
    }

    public void setEnunt(String enunt) {
        this.enunt = enunt;
    }

    public String getVarianta1() {
        return varianta1;
    }

    public void setVarianta1(String varianta1) {
        this.varianta1 = varianta1;
    }

    public String getVarianta2() {
        return varianta2;
    }

    public void setVarianta2(String varianta2) {
        this.varianta2 = varianta2;
    }

    public Integer getVariantaCorecta() {
        return variantaCorecta;
    }

    public String getVarianta3() {
        return varianta3;
    }

    public void setVarianta3(String varianta3) {
        this.varianta3 = varianta3;
    }

    public void setVariantaCorecta(Integer variantaCorecta) {
        this.variantaCorecta = variantaCorecta;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Intrebare) {
            Intrebare i = (Intrebare) obj;
            if (this.enunt.equals(i.getEnunt()) &&
                    this.domeniu.equals(i.getDomeniu()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "enunt='" + enunt + '\'' +
                ", varianta1='" + varianta1 + '\'' +
                ", varianta2='" + varianta2 + '\'' +
                ", varianta3='" + varianta3 + '\'' +
                ", variantaCorecta=" + variantaCorecta +
                ", domeniu='" + domeniu + '\'' +
                '}' + "\n";
    }

    public String intrebareToLine() {
        return getEnunt() + "#" + varianta1 + "#" + varianta2 + "#" + varianta3 + "#" + variantaCorecta +
                "#" + domeniu;
    }
}
