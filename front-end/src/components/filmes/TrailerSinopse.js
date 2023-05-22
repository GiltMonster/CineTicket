import "../../style/TrailerSinopse.css"

export default function TrailerSinopse() {
    return (
        <div class="trailer-and-sinopse">
            <div class="trailer">
                <h2>Trailer</h2>
                <iframe width="560" height="315" src="https://www.youtube.com/embed/VIDEO_ID" frameborder="0" allowfullscreen></iframe>
            </div>

            <div class="sinopse">
                <h2>Sinopse</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eget felis in risus commodo consectetur. Sed fringilla nisl vel mi lobortis congue. Proin consectetur ante felis, ac iaculis est lobortis ut. Suspendisse potenti. Nullam et mi felis. Mauris eu turpis consequat, blandit lectus sit amet, bibendum ex. Duis vitae metus ac mauris efficitur vulputate eget sit amet metus. Sed tempus dapibus enim, in convallis elit accumsan et. In sodales consequat enim, non facilisis neque luctus et. Phasellus tristique massa in ligula lobortis, vitae aliquet mauris egestas. Sed varius bibendum orci vitae hendrerit.</p>
            </div>
        </div>
    );
}