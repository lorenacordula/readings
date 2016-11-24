package readings

class Readings {

  static belongsTo = [book:Book]
  static belongsTo = [user:User]


  String status
  Date started_at
  Date finished_at
  int current_pages
  Double progress




    static constraints = {
      status inList: ["Nao Iniciado", "Em progresso", "Finalizado"]
      started_at validator: {
        if (started_at < finished_at){
          return ["A data de início da leitura deve ser anterior à data de término da leitura"]
          } }

    }
}
