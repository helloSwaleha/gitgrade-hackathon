function ResultCard({ data }) {
  return (
    <div style={{
      marginTop: "30px",
      padding: "20px",
      border: "1px solid #ccc",
      borderRadius: "10px",
      width: "70%"
    }}>
      <h2>Score: {data.score} / 100</h2>
      <h3>Level: {data.level}</h3>
      <p>{data.summary}</p>

      <h4>Personalized Roadmap</h4>
      <ul>
        {data.roadmap.map((step, index) => (
          <li key={index}>{step}</li>
        ))}
      </ul>
    </div>
  );
}

export default ResultCard;
