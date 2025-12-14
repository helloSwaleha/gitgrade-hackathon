import { useState } from "react";
import api from "../services/api";
import ResultCard from "./ResultCard";

function RepoForm() {
  const [repoUrl, setRepoUrl] = useState("");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const analyzeRepo = async () => {
    try {
      setLoading(true);
      const response = await api.post("/analyze", {
        repoUrl: repoUrl,
      });
      setResult(response.data);
    } catch (error) {
      alert("Error analyzing repository");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <input
        type="text"
        placeholder="Paste GitHub Repository URL"
        value={repoUrl}
        onChange={(e) => setRepoUrl(e.target.value)}
        style={{ width: "60%", padding: "10px" }}
      />

      <br /><br />

      <button onClick={analyzeRepo} disabled={loading}>
        {loading ? "Analyzing..." : "Analyze Repository"}
      </button>

      {result && <ResultCard data={result} />}
    </>
  );
}

export default RepoForm;
