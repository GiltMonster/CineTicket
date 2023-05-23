import "../style/carregamento.css"

export default function Loading() {

    // JavaScript para remover a tela de carregamento após um período de tempo
    window.addEventListener("load", function () {
        var loader = document.querySelector(".loader");
        loader.style.display = "none";
    });

    return (
        <div className="loader">
            <div className="spinner"></div>
        </div>
    )
}