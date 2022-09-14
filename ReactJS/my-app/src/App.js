import "./App.css";
import YoutubeList from "./components/youtube/YoutubeList";

/**
 *
 * Element = <div id="root">Hello World </div> : JSX
 * Element = React.createElement('div', { id : 'root' }, 'Hello World' );
 * fucntion createElement (elementType , props ,...childrens);
 *
 * elementType : 'div' , 'p' , 'span'
 * props : className , id , src , alt
 * ... childrens : text , tags ...
 */

// curly braces = {}
function App() {
  return (
    <div>
      <YoutubeList>
        <h2>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Cumque at non
          accusamus ipsum quam nihil harum atque iure praesentium in laboriosam
          facilis consequatur ullam tempora saepe, quia soluta, animi aliquam!
        </h2>
      </YoutubeList>
    </div>
  );
}

export default App;
